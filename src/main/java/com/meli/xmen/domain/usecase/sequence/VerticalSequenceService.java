/* (C)2023 */
package com.meli.xmen.domain.usecase.sequence;

import com.meli.xmen.domain.entity.Cell;
import com.meli.xmen.domain.usecase.sequence.util.SequenceUtil;

public class VerticalSequenceService implements SequenceService {

    @Override
    public Integer findSequences(Cell[][] matrix) {
        var total = 0;
        for (var column = 0; column < matrix.length; column++) {
            var array = new Cell[matrix.length];
            var cell = matrix[0][column];
            var counter = 0;
            array[counter] = cell;
            while (this.hasNext(cell)) {
                cell = this.getNext(matrix, cell.getRow(), cell.getColumn());
                counter += 1;
                array[counter] = cell;
            }
            total += SequenceUtil.findMutantSequenceArray(array);
        }
        return total;
    }

    private Cell getNext(Cell[][] matrix, Integer row, Integer column) {
        return matrix[row + 1][column];
    }

    private boolean hasNext(Cell cell) {
        return cell.getRow() + 1 <= cell.getSize() - 1;
    }
}
