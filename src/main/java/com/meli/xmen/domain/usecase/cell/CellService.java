/* (C)2023 */
package com.meli.xmen.domain.usecase.cell;

import com.meli.xmen.domain.entity.Cell;
import com.meli.xmen.domain.entity.ErrorResponse;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CellService {

    public Either<ErrorResponse, Cell[][]> createCells(Character[][] matrix) {

        if (matrix == null) {
            log.warn("The size of the matrix to be built is not consistent");
            return Either.left(
                    new ErrorResponse(400, "The size of the matrix to be built is not consistent"));
        }

        var cellMatrix = new Cell[matrix.length][matrix.length];
        for (var row = 0; row < matrix.length; row++) {
            for (var column = 0; column < matrix.length; column++) {
                cellMatrix[row][column] = new Cell(matrix, row, column);
            }
        }
        return Either.right(cellMatrix);
    }
}
