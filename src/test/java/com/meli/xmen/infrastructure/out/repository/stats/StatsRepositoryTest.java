/* (C)2023 */
package com.meli.xmen.infrastructure.out.repository.stats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StatsRepositoryTest {

    @InjectMocks private StatsRepository statsRepository;

    @Mock private StatsH2Port port;

    @Test
    void givenStats_whenRequestingToSave_thenSaveSuccessful() {

        final var stats = new StatsData(5, 5, 0.5, OffsetDateTime.now());
        when(port.save(stats)).thenReturn(stats);

        final var result = statsRepository.saveStats(stats);

        assertEquals(stats.getRatio(), result.getRatio());
        assertEquals(stats.getCountMutantDna(), result.getCountMutantDna());
        assertEquals(stats.getId(), result.getId());
        assertEquals(stats.getDateTime(), result.getDateTime());
    }
}
