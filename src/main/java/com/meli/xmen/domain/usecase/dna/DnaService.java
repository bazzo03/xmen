/* (C)2023 */
package com.meli.xmen.domain.usecase.dna;

import com.meli.xmen.domain.entity.DnaEntity;
import com.meli.xmen.domain.entity.ErrorResponse;
import com.meli.xmen.infrastructure.out.mapper.dna.InfrastructureDnaConverter;
import com.meli.xmen.infrastructure.out.repository.dna.DnaRepository;
import io.vavr.control.Either;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class DnaService {

    @Autowired private DnaRepository repository;

    @Autowired private InfrastructureDnaConverter infrastructureDnaConverter;

    private static final Pattern DNA_PATTERN = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);
    private static final String MATRIX_SIZE_NOT_CONSISTENT =
            "The size of the matrix to be built is not consistent";

    public DnaEntity saveDna(DnaEntity dnaEntity, Boolean isMutant) {
        return infrastructureDnaConverter.convertFromRepositoryEntityToDomainEntity(
                repository.saveDna(
                        infrastructureDnaConverter.convertFromDomainEntityToRepositoryEntity(
                                dnaEntity, isMutant)));
    }

    public Either<ErrorResponse, char[][]> loadDnaData(DnaEntity dnaEntity) {
        log.info("Will transform DNA into a matrix of Characters");
        var dnaResult = new char[dnaEntity.getDnaList().size()][dnaEntity.getDnaList().size()];

        var counter = new AtomicInteger(0);
        var validations =
                dnaEntity.getDnaList().stream()
                        .map(
                                row ->
                                        validateRow(dnaEntity.getDnaList().size(), row)
                                                .fold(
                                                        left -> {
                                                            counter.getAndIncrement();
                                                            return Either.left(left);
                                                        },
                                                        right -> {
                                                            dnaResult[counter.get()] =
                                                                            row.toUpperCase()
                                                                                    .toCharArray();
                                                            counter.getAndIncrement();
                                                            return Either.right(dnaResult);
                                                        }))
                        .toList();

        var validationsList = validations.stream().filter(Either::isLeft).toList();
        if (validationsList.isEmpty()) {
            return Either.right(dnaResult);
        } else {
            return (Either<ErrorResponse, char[][]>)
                    validationsList.stream().findFirst().orElse(returnGenericErrorResponse());
        }
    }

    private <T> Either<ErrorResponse, T> returnGenericErrorResponse() {
        return Either.left(new ErrorResponse(400, MATRIX_SIZE_NOT_CONSISTENT));
    }

    private Either<ErrorResponse, Boolean> validateRow(int vectorLength, String row) {
        if (row.length() != vectorLength) {
            log.warn(MATRIX_SIZE_NOT_CONSISTENT);
            return Either.left(new ErrorResponse(400, MATRIX_SIZE_NOT_CONSISTENT));
        } else if (!DNA_PATTERN.matcher(row).matches()) {
            log.warn(
                    "Invalid Character found: {}. The only valid Characters are: A, T, C, G.", row);
            return Either.left(
                    new ErrorResponse(
                            400,
                            String.format(
                                    "Invalid character found in row: %s. The only valid Characters"
                                            + " are: A, T, C, G.",
                                    row)));
        }
        return Either.right(true);
    }
}
