/* (C)2023 */
package com.meli.xmen.domain.usecase.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.meli.xmen.domain.usecase.Sample;
import org.junit.jupiter.api.Test;

class CellServiceTest {

    CellService cellService = new CellService();

    @Test
    void givenCharacterMatrix_whenCreateCellsIsCalled_thenShouldCreateAllTheCellsSuccessful() {
        var response = cellService.createCells(Sample.createMutantMatrix());
        assertTrue(response.isRight());
        assertEquals(Sample.createMutantMatrix().length, response.get().length);
    }

    @Test
    void givenNullCharacterMatrix_whenCreateCellsIsCalled_thenShouldReturnLeftSuccessful() {
        var response = cellService.createCells(null);
        assertTrue(response.isLeft());
        assertEquals(400, response.getLeft().getCode());
    }
}
