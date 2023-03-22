/* (C)2023 */
package com.meli.xmen.domain.usecase.dna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.meli.xmen.domain.entity.Dna;
import com.meli.xmen.domain.usecase.Sample;
import org.junit.jupiter.api.Test;

class DnaServiceTest {

    private DnaService dnaService = new DnaService();

    @Test
    void givenACorrectMatrix_whenLoadDnaDataIsCalled_thenShouldReturnTheMatrixAsCharacterArray() {

        final var response =
                dnaService.loadDnaData(
                        Dna.builder().withDnaList(Sample.createMutantCharacterList()).build());
        assertTrue(response.isRight());
        assertEquals(Sample.createMutantCharacterList().get(0).length(), response.get().length);
    }

    @Test
    void givenAnIncorrectMatrix_whenLoadDnaDataIsCalled_thenShouldReturnTheError() {

        final var response =
                dnaService.loadDnaData(
                        Dna.builder()
                                .withDnaList(Sample.createMutantWithErrorCharacterList())
                                .build());
        assertTrue(response.isLeft());
        assertEquals(400, response.getLeft().getCode());
    }
}
