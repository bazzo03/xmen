package com.meli.xmen.domain.usecase.stats;

import com.meli.xmen.domain.entity.StatsEntity;
import com.meli.xmen.infrastructure.out.mapper.stats.InfrastructureStatsConverter;
import com.meli.xmen.infrastructure.out.repository.stats.StatsData;
import com.meli.xmen.infrastructure.out.repository.stats.StatsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@SpringBootTest
class StatsServiceTest {

    @InjectMocks
    private StatsService statsService;

    @Mock
    private StatsRepository repository;

    @Mock
    private InfrastructureStatsConverter infrastructureStatsConverter;

    @Test
    void givenThereAreNoStats_whenStatsRequired_thenShouldRespondCorrectly() {


        given(repository.getCurrentStats()).willReturn(new StatsData(0,0,0.0, OffsetDateTime.now()));
        given(infrastructureStatsConverter.convertFromRepositoryEntityToDomainEntity(any())).willReturn(new StatsEntity(0,0,0.0));

        final var response = statsService.getStats();

        assertTrue(response.isRight());
        assertEquals(0, response.get().getCountMutantDna());
        assertEquals(0, response.get().getCountHumanDna());
        assertEquals(0.0, response.get().getRatio());
    }
    @Test
    void givenThereAreStats_whenStatsRequired_thenShouldRespondCorrectly() {


        given(repository.getCurrentStats()).willReturn(new StatsData(1,0,1.0, OffsetDateTime.now()));
        given(infrastructureStatsConverter.convertFromRepositoryEntityToDomainEntity(any())).willReturn(new StatsEntity(1,0,1.0));

        final var response = statsService.getStats();

        assertTrue(response.isRight());
        assertEquals(1, response.get().getCountMutantDna());
        assertEquals(0, response.get().getCountHumanDna());
        assertEquals(1.0, response.get().getRatio());
    }


}
