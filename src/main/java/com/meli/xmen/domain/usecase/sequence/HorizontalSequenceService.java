/* (C)2023 */
package com.meli.xmen.domain.usecase.sequence;

import com.meli.xmen.domain.entity.Cell;
import com.meli.xmen.domain.usecase.sequence.util.SequenceUtil;

public class HorizontalSequenceService implements SequenceService {

    @Override
    public Integer findSequences(Cell[][] matrix) {
        Integer total = 0;
        for (var row = 0; row < matrix.length; row++) {
            var array = new Cell[matrix.length];
            var cell = matrix[row][0];
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
        return matrix[row][column + 1];
    }

    private boolean hasNext(Cell cell) {
        return cell.getColumn() + 1 <= cell.getSize() - 1;
    }
}
