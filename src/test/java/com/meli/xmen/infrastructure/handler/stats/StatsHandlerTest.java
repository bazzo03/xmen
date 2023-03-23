/* (C)2023 */
package com.meli.xmen.infrastructure.handler.stats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import com.meli.xmen.domain.entity.ErrorResponse;
import com.meli.xmen.domain.entity.Stats;
import com.meli.xmen.domain.usecase.stats.StatsService;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StatsHandlerTest {

    @InjectMocks private StatsHandler handler;

    @Mock private StatsService service;

    @Test
    void givenAStatsRequest_whenInfoExists_thenShouldReturnDataSuccessful() {

        given(service.getStats()).willReturn(Either.right(new Stats(10, 10, 1.0)));
        var response = handler.getStats();
        assertTrue(response.isRight());
        assertEquals(10, response.get().getCountHumanDna());
        assertEquals(10, response.get().getCountMutantDna());
    }

    @Test
    void givenAStatsRequest_whenErrorExists_thenShouldReturnErrorSuccessful() {

        given(service.getStats()).willReturn(Either.left(new ErrorResponse(500, "Internal Error")));
        var response = handler.getStats();
        assertTrue(response.isLeft());
        assertEquals(500, response.getLeft().getCode());
        assertEquals("Internal Error", response.getLeft().getMessage());
    }
}
