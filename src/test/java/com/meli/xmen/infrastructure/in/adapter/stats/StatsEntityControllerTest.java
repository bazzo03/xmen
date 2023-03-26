/* (C)2023 */
package com.meli.xmen.infrastructure.in.adapter.stats;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.meli.xmen.infrastructure.in.handler.stats.StatsHandler;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StatsEntityControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private WebApplicationContext wac;

    @Mock private StatsHandler handler;

    @Test
    void givenSomeStats_whenRequested_thenShouldReturnStats() throws Exception {

        this.mockMvc
                .perform(get("/api/v1/stats").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").isEmpty())
                .andExpect(jsonPath("$.stats").isNotEmpty());
    }
}
