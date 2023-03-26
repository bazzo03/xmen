/* (C)2023 */
package com.meli.xmen.infrastructure.out.mapper.stats;

import com.meli.xmen.domain.entity.StatsEntity;
import com.meli.xmen.infrastructure.out.repository.stats.StatsData;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class InfrastructureStatsConverter {

    public StatsData convertFromDomainEntityToRepositoryEntity(StatsEntity domainStatsEntity) {
        return new StatsData(
                domainStatsEntity.getCountMutantDna(),
                domainStatsEntity.getCountHumanDna(),
                domainStatsEntity.getRatio(),
                OffsetDateTime.now());
    }

    public StatsEntity convertFromRepositoryEntityToDomainEntity(StatsData statsData) {
        return new StatsEntity(
                statsData.getCountMutantDna(), statsData.getCountHumanDna(), statsData.getRatio());
    }
}
