/* (C)2023 */
package com.meli.xmen.domain.usecase.dna.sequence;

import com.meli.xmen.domain.entity.CellEntity;

public interface SequenceService {

    Integer findSequences(CellEntity[][] matrix);
}
