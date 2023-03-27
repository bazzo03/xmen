package com.meli.xmen.domain.usecase.dna.sequence;


import com.meli.xmen.domain.usecase.Sample;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DiagonalSequenceServiceTest {

    @InjectMocks
    private DiagonalSequenceService diagonalSequenceService;

    @Test
    void givenACellEntityMatrixWithMutantData_whenRequestingToFindSequences_thenFindSequences() {

        final var matrix = Sample.convertFromCharsMatrixToCellEntityMatrix(Sample.createMutantMatrix());
        final var response = diagonalSequenceService.findSequences(matrix);

        assertEquals(1, response);
    }

    @Test
    void givenACellEntityMatrixWithHumanData_whenRequestingToFindSequences_thenDoNotFindSequences() {

        final var matrix = Sample.convertFromCharsMatrixToCellEntityMatrix(Sample.createHumanMatrix());
        final var response = diagonalSequenceService.findSequences(matrix);

        assertEquals(0, response);
    }

}
