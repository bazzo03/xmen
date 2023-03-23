/* (C)2023 */
package com.meli.xmen.infrastructure.adapter.stats;


import com.meli.xmen.domain.entity.ErrorResponse;
import com.meli.xmen.infrastructure.handler.stats.StatsHandler;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired private WebApplicationContext wac;

    @Mock
    private StatsHandler handler;

    @Test
    void givenSomeStats_whenRequested_thenShouldReturnStats() throws Exception {

        this.mockMvc
                .perform(get("/api/v1/stats").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").isEmpty())
                .andExpect(jsonPath("$.stats").isNotEmpty());
    }

}
