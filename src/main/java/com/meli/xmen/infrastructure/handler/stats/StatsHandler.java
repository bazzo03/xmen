/* (C)2023 */
package com.meli.xmen.infrastructure.handler.stats;

import com.meli.xmen.domain.entity.ErrorResponse;
import com.meli.xmen.domain.usecase.stats.StatsService;
import com.meli.xmen.infrastructure.adapter.stats.StatsValueResponse;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class StatsHandler {

    private StatsService statsService;

    public Either<ErrorResponse, StatsValueResponse> getStats() {

        return statsService
                .getStats()
                .fold(
                        Either::left,
                        right ->
                                Either.right(
                                        new StatsValueResponse(
                                                right.getCountMutantDna(),
                                                right.getCountHumanDna(),
                                                right.getRatio())));
    }
}
