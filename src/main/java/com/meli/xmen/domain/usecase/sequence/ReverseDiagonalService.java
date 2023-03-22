/* (C)2023 */
package com.meli.xmen.domain.usecase.sequence;

import com.meli.xmen.domain.entity.Cell;
import com.meli.xmen.domain.usecase.SequenceUtil;

public class ReverseDiagonalService implements SequenceService {

    @Override
    public Integer findSequences(Cell[][] matrix) {
        int total = 0;
        Cell[] array = new Cell[matrix.length];
        var cell = matrix[matrix.length - 1][0];
        int counter = 0;
        array[counter] = cell;
        while (this.hasNext(cell)) {
            cell = this.getNext(matrix, cell.getRow(), cell.getColumn());
            counter += 1;
            array[counter] = cell;
        }
        total += SequenceUtil.findMutantSequenceArray(array);
        return total;
    }

    private Cell getNext(Cell[][] matrix, int row, int column) {
        return matrix[row - 1][column + 1];
    }

    private boolean hasNext(Cell cell) {
        return cell.getRow() - 1 >= 0 && cell.getColumn() + 1 < cell.getDna().length;
    }
}
