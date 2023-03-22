/* (C)2023 */
package com.meli.xmen.domain.usecase.sequence;

import com.meli.xmen.domain.entity.Cell;

public interface SequenceService {

    Integer findSequences(Cell[][] matrix);
}
