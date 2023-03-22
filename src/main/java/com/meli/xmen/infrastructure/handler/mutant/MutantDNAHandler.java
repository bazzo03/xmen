/* (C)2023 */
package com.meli.xmen.infrastructure.handler.mutant;

import com.meli.xmen.domain.entity.Cell;
import com.meli.xmen.domain.entity.ErrorResponse;
import com.meli.xmen.domain.usecase.cell.CellService;
import com.meli.xmen.domain.usecase.dna.DnaService;
import com.meli.xmen.domain.usecase.sequence.DiagonalSequenceService;
import com.meli.xmen.domain.usecase.sequence.HorizontalSequenceService;
import com.meli.xmen.domain.usecase.sequence.ReverseDiagonalService;
import com.meli.xmen.domain.usecase.sequence.VerticalSequenceService;
import com.meli.xmen.infrastructure.adapter.mutant.DnaRequest;
import com.meli.xmen.infrastructure.mapper.mutant.MutantConverter;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class MutantDNAHandler {

    @Autowired private MutantConverter mutantConverter;
    private CellService cellService;
    private DnaService dnaService;
    private VerticalSequenceService verticalSequenceService;

    private HorizontalSequenceService horizontalSequenceService;

    private DiagonalSequenceService diagonalSequenceService;

    private ReverseDiagonalService reverseDiagonalService;

    public Either<ErrorResponse, Boolean> findIsMutant(DnaRequest dna) {

        Either<ErrorResponse, Cell[][]> result =
                dnaService
                        .loadDnaData(mutantConverter.convertFromRequestToEntity(dna))
                        .fold(
                                left -> {
                                    log.warn(left.getMessage());
                                    return Either.left(left);
                                },
                                right -> cellService.createCells(right));

        return result.fold(Either::left, this::findAllDirectionSequences);
    }

    private <T> Either<T, Boolean> findAllDirectionSequences(Cell[][] matrix) {
        var v = verticalSequenceService.findSequences(matrix);
        var h = horizontalSequenceService.findSequences(matrix);
        var d = diagonalSequenceService.findSequences(matrix);
        var rd = reverseDiagonalService.findSequences(matrix);

        System.out.println("WA");
        System.out.println(v + h + d + rd);
        System.out.println("WA");
        return Either.right(v + h + d + rd >= 2);
    }
}
