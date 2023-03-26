/* (C)2023 */
package com.meli.xmen.domain.usecase.dna.sequence;

import com.meli.xmen.domain.entity.CellEntity;
import com.meli.xmen.domain.usecase.dna.sequence.util.SequenceUtil;

public class VerticalSequenceService implements SequenceService {

    @Override
    public Integer findSequences(CellEntity[][] matrix) {
        var total = 0;
        for (var column = 0; column < matrix.length; column++) {
            var array = new CellEntity[matrix.length];
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

    private CellEntity getNext(CellEntity[][] matrix, Integer row, Integer column) {
        return matrix[row + 1][column];
    }

    private boolean hasNext(CellEntity cellEntity) {
        return cellEntity.getRow() + 1 <= cellEntity.getSize() - 1;
    }
}
