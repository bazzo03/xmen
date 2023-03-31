/* (C)2023 */
package com.meli.xmen.infrastructure.out.repository.stats;

import io.vavr.control.Try;
import java.time.OffsetDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class StatsRepository {

    @Autowired private StatsH2Port h2Repository;

    public StatsData getCurrentStats() {
        h2Repository.findAll().forEach(item -> log.info("item :" + item.toString()));
        return Try.of(() -> h2Repository.findAll())
                .map(results -> results.iterator().next())
                .recover(
                        t -> {
                            log.warn("No data found in the database");
                            return new StatsData(0, 0, 0.0, OffsetDateTime.now());
                        })
                .get();
    }

    public StatsData saveStats(StatsData stats) {
        return h2Repository.save(stats);
    }
}
