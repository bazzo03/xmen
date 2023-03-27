package com.meli.xmen.infrastructure.in.handler.dna;

import com.meli.xmen.domain.entity.DnaEntity;
import com.meli.xmen.domain.entity.ErrorResponse;
import com.meli.xmen.domain.entity.StatsEntity;
import com.meli.xmen.domain.usecase.Sample;
import com.meli.xmen.domain.usecase.dna.DnaService;
import com.meli.xmen.domain.usecase.dna.cell.CellService;
import com.meli.xmen.domain.usecase.dna.sequence.DiagonalSequenceService;
import com.meli.xmen.domain.usecase.dna.sequence.HorizontalSequenceService;
import com.meli.xmen.domain.usecase.dna.sequence.ReverseDiagonalSequenceService;
import com.meli.xmen.domain.usecase.dna.sequence.VerticalSequenceService;
import com.meli.xmen.domain.usecase.stats.StatsService;
import com.meli.xmen.infrastructure.in.adapter.dna.DnaRequest;
import com.meli.xmen.infrastructure.in.mapper.dna.DomainDnaConverter;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class DnaHandlerTest {


    @InjectMocks
    private DnaHandler dnaHandler;

    @Mock
    private DomainDnaConverter domainDnaConverter;

    @Mock
    private CellService cellService;

    @Mock
    private DnaService dnaService;

    @Mock
    private StatsService statsService;

    @Mock
    private VerticalSequenceService verticalSequenceService;

    @Mock
    private HorizontalSequenceService horizontalSequenceService;

    @Mock
    private DiagonalSequenceService diagonalSequenceService;

    @Mock
    private ReverseDiagonalSequenceService reverseDiagonalService;


    @Test
    void givenARequest_whenFindingIfIsMutant_thenReturnTrue() {

        final var dnaEntity = new DnaEntity(Sample.createMutantCharacterList());
        final var dnaRequest = new DnaRequest(Sample.createMutantCharacterList());
        final var domainDnaAsChars = Sample.createMutantMatrix();
        final var domainDnaAsCells = Sample.convertFromCharsMatrixToCellEntityMatrix(domainDnaAsChars);
        final var statsEntity = new StatsEntity(1, 0, 1.0);

        given(domainDnaConverter.convertFromRequestToDomainEntity(any())).willReturn(dnaEntity);
        given(dnaService.loadDnaData(dnaEntity)).willReturn(Either.right(domainDnaAsChars));
        given(cellService.createCells(domainDnaAsChars)).willReturn(Either.right(domainDnaAsCells));
        given(domainDnaConverter.convertFromEntityToDomainRequest(dnaEntity)).willReturn(dnaRequest);
        given(dnaService.saveDna(dnaEntity, true)).willReturn(dnaEntity);
        given(statsService.generateStats(true)).willReturn(Either.right(statsEntity));

        given(verticalSequenceService.findSequences(domainDnaAsCells)).willReturn(1);
        given(horizontalSequenceService.findSequences(domainDnaAsCells)).willReturn(1);
        given(diagonalSequenceService.findSequences(domainDnaAsCells)).willReturn(1);
        given(reverseDiagonalService.findSequences(domainDnaAsCells)).willReturn(1);

        final var response = dnaHandler.findIsMutant(dnaRequest);

        assertTrue(response.isRight());
        assertTrue(response.get());
    }

    @Test
    void givenARequest_whenFindingIfIsNotMutant_thenReturnFalse() {

        final var dnaEntity = new DnaEntity(Sample.createMutantCharacterList());
        final var dnaRequest = new DnaRequest(Sample.createMutantCharacterList());
        final var domainDnaAsChars = Sample.createMutantMatrix();
        final var domainDnaAsCells = Sample.convertFromCharsMatrixToCellEntityMatrix(domainDnaAsChars);
        final var statsEntity = new StatsEntity(1, 0, 1.0);

        given(domainDnaConverter.convertFromRequestToDomainEntity(any())).willReturn(dnaEntity);
        given(dnaService.loadDnaData(dnaEntity)).willReturn(Either.right(domainDnaAsChars));
        given(cellService.createCells(domainDnaAsChars)).willReturn(Either.right(domainDnaAsCells));
        given(domainDnaConverter.convertFromEntityToDomainRequest(dnaEntity)).willReturn(dnaRequest);
        given(dnaService.saveDna(dnaEntity, true)).willReturn(dnaEntity);
        given(statsService.generateStats(true)).willReturn(Either.right(statsEntity));

        given(verticalSequenceService.findSequences(domainDnaAsCells)).willReturn(0);
        given(horizontalSequenceService.findSequences(domainDnaAsCells)).willReturn(0);
        given(diagonalSequenceService.findSequences(domainDnaAsCells)).willReturn(0);
        given(reverseDiagonalService.findSequences(domainDnaAsCells)).willReturn(1);

        final var response = dnaHandler.findIsMutant(dnaRequest);

        assertTrue(response.isRight());
        assertFalse(response.get());
    }
    @Test
    void givenARequestWithWrongCharacters_whenFindingIfIsNotMutant_thenReturnError() {

        final var dnaEntity = new DnaEntity(Sample.createMutantWithErrorCharacterList());
        final var dnaRequest = new DnaRequest(Sample.createMutantWithErrorCharacterList());

        given(domainDnaConverter.convertFromRequestToDomainEntity(any())).willReturn(dnaEntity);
        given(dnaService.loadDnaData(dnaEntity)).willReturn(Either.left(new ErrorResponse(400, "Invalid Character found")));

        final var response = dnaHandler.findIsMutant(dnaRequest);

        assertTrue(response.isLeft());
        assertTrue(response.getLeft().getMessage().contains("Invalid"));
        assertEquals(400, response.getLeft().getCode());
    }


}
