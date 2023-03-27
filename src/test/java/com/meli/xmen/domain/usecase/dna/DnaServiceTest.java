/* (C)2023 */
package com.meli.xmen.domain.usecase.dna;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.meli.xmen.domain.entity.DnaEntity;
import com.meli.xmen.domain.usecase.Sample;
import com.meli.xmen.infrastructure.out.mapper.dna.InfrastructureDnaConverter;
import com.meli.xmen.infrastructure.out.repository.dna.DnaData;
import com.meli.xmen.infrastructure.out.repository.dna.DnaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DnaServiceTest {
    @InjectMocks
    private DnaService dnaService;

    @Mock
    private DnaRepository repository;

    @Mock private InfrastructureDnaConverter infrastructureDnaConverter;

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

    void givenDnaEntityAndIsMutant_whenAskedToSaveData_thenSaveSuccessful() {

        given(infrastructureDnaConverter.convertFromRepositoryEntityToDomainEntity(
                new DnaData(Sample.createMutantCharacterList(), true)))
                .willReturn(new DnaEntity(Sample.createMutantCharacterList()));
        given(repository.saveDna(
                new DnaData(1, Sample.createMutantCharacterList(), true)))
                .willReturn(new DnaData(Sample.createMutantCharacterList(), true));
        given(
                infrastructureDnaConverter.convertFromDomainEntityToRepositoryEntity(new DnaEntity(Sample.createMutantCharacterList()), true))
                .willReturn(new DnaData(1, Sample.createMutantCharacterList(), true));



        final var response = dnaService.saveDna(
                new DnaEntity(Sample.createMutantCharacterList()), true);

        assertFalse(response.getDnaList().isEmpty());
        assertEquals(Sample.createMutantCharacterList().size(), response.getDnaList().size());


    }

}
