/* (C)2023 */
package com.meli.xmen.infrastructure.out.repository.dna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DnaRepositoryTest {

    @InjectMocks private DnaRepository dnaRepository;

    @Mock private DnaH2Port port;

    @Test
    void givenData_whenRequestingToSave_thenSaveSuccessful() {

        final var dnaData = new DnaData(List.of("ACGT, TGCA"), true);

        when(port.save(dnaData)).thenReturn(dnaData);

        final var result = dnaRepository.saveDna(dnaData);

        assertEquals(dnaData.getDnaList().size(), result.getDnaList().size());
    }
}
