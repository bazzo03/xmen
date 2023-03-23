/* (C)2023 */
package com.meli.xmen.domain.usecase.stats;

import com.meli.xmen.domain.entity.ErrorResponse;
import com.meli.xmen.domain.entity.Stats;
import io.vavr.control.Either;

public class StatsService {
    public Either<ErrorResponse, Stats> getStats() {
        return Either.right(new Stats(0, 0, 0.0));
    }
}
