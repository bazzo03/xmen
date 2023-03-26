/* (C)2023 */
package com.meli.xmen.domain.usecase.stats;

import com.meli.xmen.domain.entity.ErrorResponse;
import com.meli.xmen.domain.entity.StatsEntity;
import com.meli.xmen.infrastructure.out.mapper.stats.InfrastructureStatsConverter;
import com.meli.xmen.infrastructure.out.repository.stats.StatsRepository;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;

public class StatsService {

    @Autowired private StatsRepository repository;

    @Autowired private InfrastructureStatsConverter infrastructureStatsConverter;

    private static final Integer INCREMENT = 1;

    public Either<ErrorResponse, StatsEntity> getStats() {
        return Either.right(infrastructureStatsConverter.convertFromRepositoryEntityToDomainEntity(repository.getCurrentStats()));

    }

    public Either<ErrorResponse, StatsEntity> generateStats(Boolean isMutant) {
        StatsEntity currentStats = infrastructureStatsConverter.convertFromRepositoryEntityToDomainEntity(repository.getCurrentStats());
        return Either.right(infrastructureStatsConverter.convertFromRepositoryEntityToDomainEntity(
                repository.saveStats(
                        infrastructureStatsConverter.convertFromDomainEntityToRepositoryEntity(calculateNewStats(currentStats, isMutant)))
        ));
    }

    private StatsEntity calculateNewStats(StatsEntity currentStats, Boolean isMutant) {
        if (isMutant) {
            return new StatsEntity(
                    currentStats.getCountMutantDna() + INCREMENT,
                    currentStats.getCountHumanDna(),
                    calculateRatio(Double.valueOf(currentStats.getCountMutantDna() + INCREMENT),
                            Double.valueOf(currentStats.getCountHumanDna())));
        } else {
            final var ratio = Double.valueOf(currentStats.getCountMutantDna()) / Double.valueOf(currentStats.getCountHumanDna() + INCREMENT);
            return new StatsEntity(
                    currentStats.getCountMutantDna(),
                    currentStats.getCountHumanDna() + INCREMENT,
                    calculateRatio(Double.valueOf(currentStats.getCountMutantDna()),
                            Double.valueOf(currentStats.getCountHumanDna() + INCREMENT)));
        }
    }

    private Double calculateRatio(Double mutantDna, Double humanDna) {
        if (humanDna == 0) { //if there are no humans, then the existing dna belongs to mutant
            return 1.0;
        }
        if (mutantDna == 0) { //if there are no humans, then the existing dna belongs to mutant
            return 0.0;
        }
        return mutantDna / humanDna;
    }
}
