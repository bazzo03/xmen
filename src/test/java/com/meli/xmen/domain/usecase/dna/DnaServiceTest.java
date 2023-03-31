/* (C)2023 */
package com.meli.xmen.domain.usecase.dna;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.meli.xmen.domain.entity.DnaEntity;
import com.meli.xmen.domain.usecase.Sample;
import com.meli.xmen.infrastructure.out.mapper.dna.InfrastructureDnaConverter;
import com.meli.xmen.infrastructure.out.repository.dna.DnaData;
import com.meli.xmen.infrastructure.out.repository.dna.DnaRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DnaServiceTest {
    @InjectMocks private DnaService dnaService;
    @Mock private DnaRepository repository;
    @Mock private InfrastructureDnaConverter infrastructureDnaConverter;

    @Test
    void givenAnEmptyMatrix_whenLoadDnaDataIsCalled_thenShouldReturnTheCorrespondingError() {

        final var response = dnaService.loadDnaData(new DnaEntity(List.of()));
        assertTrue(response.isLeft());
        assertEquals(400, response.getLeft().getCode());
    }

    @Test
    void givenBigMatrix_whenLoadDnaDataIsCalled_thenShouldReturnTheCorrespondingError() {

        final var response =
                dnaService.loadDnaData(new DnaEntity(Sample.createGiantCharacterList()));
        assertTrue(response.isLeft());
        assertEquals(400, response.getLeft().getCode());
    }

    @Test
    void givenACorrectMatrix_whenLoadDnaDataIsCalled_thenShouldReturnTheMatrixAsCharacterArray() {

        final var response =
                dnaService.loadDnaData(new DnaEntity(Sample.createMutantCharacterList()));
        assertTrue(response.isRight());
        assertEquals(Sample.createMutantCharacterList().get(0).length(), response.get().length);
    }

    @Test
    void givenAnIncorrectMatrix_whenLoadDnaDataIsCalled_thenShouldReturnTheError() {

        final var response =
                dnaService.loadDnaData(new DnaEntity(Sample.createMutantWithErrorCharacterList()));
        assertTrue(response.isLeft());
        assertEquals(400, response.getLeft().getCode());
    }

    @Test
    void givenDnaEntityAndIsMutant_whenAskedToSaveData_thenSaveSuccessful() {

        dnaService = new DnaService(repository, infrastructureDnaConverter);

        var dnaData = new DnaData(Sample.createMutantCharacterList(), true);
        var dnaEntity = new DnaEntity(Sample.createMutantCharacterList());

        given(infrastructureDnaConverter.convertFromRepositoryEntityToDomainEntity(dnaData))
                .willReturn(new DnaEntity(Sample.createMutantCharacterList()));
        given(repository.saveDna(dnaData)).willReturn(dnaData);
        given(infrastructureDnaConverter.convertFromDomainEntityToRepositoryEntity(dnaEntity, true))
                .willReturn(dnaData);

        final var response = dnaService.saveDna(dnaEntity, true);

        assertFalse(response.getDnaList().isEmpty());
        assertEquals(Sample.createMutantCharacterList().size(), response.getDnaList().size());
    }
}
