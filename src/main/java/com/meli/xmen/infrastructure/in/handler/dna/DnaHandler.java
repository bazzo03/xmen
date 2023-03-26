/* (C)2023 */
package com.meli.xmen.infrastructure.in.handler.dna;

import com.meli.xmen.domain.entity.CellEntity;
import com.meli.xmen.domain.entity.ErrorResponse;
import com.meli.xmen.domain.usecase.dna.DnaService;
import com.meli.xmen.domain.usecase.dna.cell.CellService;
import com.meli.xmen.domain.usecase.dna.sequence.DiagonalSequenceService;
import com.meli.xmen.domain.usecase.dna.sequence.HorizontalSequenceService;
import com.meli.xmen.domain.usecase.dna.sequence.ReverseDiagonalService;
import com.meli.xmen.domain.usecase.dna.sequence.VerticalSequenceService;
import com.meli.xmen.domain.usecase.stats.StatsService;
import com.meli.xmen.infrastructure.in.adapter.dna.DnaRequest;
import com.meli.xmen.infrastructure.in.mapper.dna.DomainDnaConverter;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class DnaHandler {

    private static final Integer MINIMUM_SEQUENCE = 1;

    @Autowired private DomainDnaConverter domainDnaConverter;

    private CellService cellService;

    private DnaService dnaService;

    private StatsService statsService;

    private VerticalSequenceService verticalSequenceService;

    private HorizontalSequenceService horizontalSequenceService;

    private DiagonalSequenceService diagonalSequenceService;

    private ReverseDiagonalService reverseDiagonalService;

    public Either<ErrorResponse, Boolean> findIsMutant(DnaRequest dna) {

        var domainDna = domainDnaConverter.convertFromRequestToDomainEntity(dna);
        Either<ErrorResponse, CellEntity[][]> result =
                dnaService
                        .loadDnaData(domainDna)
                        .fold(
                                left -> {
                                    log.warn(left.getMessage());
                                    return Either.left(left);
                                },
                                right -> cellService.createCells(right));

        result.map(
                right ->
                        findAllDirectionSequences(right)
                                .map(
                                        bool -> {
                                            domainDnaConverter.convertFromEntityToDomainRequest(
                                                    dnaService.saveDna(domainDna, bool));

                                            statsService.generateStats(bool);
                                            return null;
                                        }));

        return result.fold(Either::left, this::findAllDirectionSequences);
    }

    private <T> Either<T, Boolean> findAllDirectionSequences(CellEntity[][] matrix) {
        return Either.right(
                verticalSequenceService.findSequences(matrix)
                                + horizontalSequenceService.findSequences(matrix)
                                + diagonalSequenceService.findSequences(matrix)
                                + reverseDiagonalService.findSequences(matrix)
                        > MINIMUM_SEQUENCE);
    }
}
