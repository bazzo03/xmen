/* (C)2023 */
package com.meli.xmen.domain.usecase.dna;

import com.meli.xmen.domain.entity.Dna;
import com.meli.xmen.domain.entity.ErrorResponse;
import io.vavr.control.Either;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

@Slf4j
public class DnaService {

    private static final Pattern DNA_PATTERN = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);
    private static final String MATRIX_SIZE_NOT_CONSISTENT =
            "The size of the matrix to be built is not consistent";

    public Either<ErrorResponse, Character[][]> loadDnaData(Dna dna) {
        log.info("Will transform DNA into a matrix of Characters");
        var dnaResult = new Character[dna.getDnaList().size()][dna.getDnaList().size()];

        var counter = new AtomicInteger(0);
        var validations =
                dna.getDnaList().stream()
                        .map(
                                row ->
                                        validateRow(dna.getDnaList().size(), row)
                                                .fold(
                                                        left -> {
                                                            counter.getAndIncrement();
                                                            return Either.left(left);
                                                        },
                                                        right -> {
                                                            dnaResult[counter.get()] =
                                                                    ArrayUtils.toObject(
                                                                            row.toUpperCase()
                                                                                    .toCharArray());
                                                            counter.getAndIncrement();
                                                            return Either.right(dnaResult);
                                                        }))
                        .toList();

        var x = validations.stream().filter(Either::isLeft).toList();
        if (x.isEmpty()) {
            return Either.right(dnaResult);
        } else {
            return (Either<ErrorResponse, Character[][]>)
                    x.stream().findFirst().orElse(returnGenericErrorResponse());
        }
    }

    private <T> Either<ErrorResponse, T> returnGenericErrorResponse() {
        return Either.left(
                new ErrorResponse(400, MATRIX_SIZE_NOT_CONSISTENT));
    }

    private Either<ErrorResponse, Boolean> validateRow(Integer vectorLength, String row) {
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
        return Either.right(Boolean.TRUE);
    }
}
