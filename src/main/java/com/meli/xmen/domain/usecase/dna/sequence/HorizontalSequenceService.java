/* (C)2023 */
package com.meli.xmen.domain.usecase.dna.sequence;

import com.meli.xmen.domain.entity.CellEntity;
import com.meli.xmen.domain.usecase.dna.sequence.util.SequenceUtil;

public class HorizontalSequenceService implements SequenceService {

    @Override
    public int findSequences(CellEntity[][] matrix) {
        int total = 0;
        for (var row = 0; row < matrix.length; row++) {
            var array = new CellEntity[matrix.length];
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

    private CellEntity getNext(CellEntity[][] matrix, int row, int column) {
        return matrix[row][column + 1];
    }

    private boolean hasNext(CellEntity cellEntity) {
        return cellEntity.getColumn() + 1 <= cellEntity.getSize() - 1;
    }
}
