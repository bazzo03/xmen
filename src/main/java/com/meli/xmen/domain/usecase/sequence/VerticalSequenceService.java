/* (C)2023 */
package com.meli.xmen.domain.usecase.sequence;

import com.meli.xmen.domain.entity.Cell;
import com.meli.xmen.domain.usecase.SequenceUtil;

public class VerticalSequenceService implements SequenceService {

    @Override
    public Integer findSequences(Cell[][] matrix) {
        int total = 0;
        for (int column = 0; column < matrix.length; column++) {
            Cell[] array = new Cell[matrix.length];
            var cell = matrix[0][column];
            int counter = 0;
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

    private Cell getNext(Cell[][] matrix, int row, int column) {
        return matrix[row + 1][column];
    }

    private boolean hasNext(Cell cell) {
        return cell.getRow() + 1 <= cell.getSize() - 1;
    }
}
