/* (C)2023 */
package com.meli.xmen.infrastructure.adapter.mutant;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.xmen.domain.usecase.Sample;
import com.meli.xmen.infrastructure.handler.mutant.MutantDNAHandler;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MutantDnaControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private WebApplicationContext wac;

    @Autowired private ObjectMapper objectMapper;

    @Mock private MutantDNAHandler handler;

    @Test
    void givenAListOfStrings_whenFindingIsMutant_thenShouldReturnTrue() throws Exception {

        var dto = new DnaRequest(Sample.createMutantCharacterList());

        this.mockMvc
                .perform(
                        post("/api/v1/mutants")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void givenAListOfStrings_whenFindingIsMutant_thenShouldReturnTrue2() throws Exception {

        var dto = new DnaRequest(Sample.createMutantCharacterList());
        when(handler.findIsMutant(new DnaRequest(anyList())))
                .thenReturn(Either.right(Boolean.TRUE));

        this.mockMvc
                .perform(
                        post("/api/v1/mutants")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void givenAWrongListOfStrings_whenFindingIsMutant_thenShouldReturn400Error() throws Exception {

        var dto = new DnaRequest(Sample.createWrongMutantCharacterList());

        this.mockMvc
                .perform(
                        post("/api/v1/mutants")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsStringIgnoringCase("Invalid Character found")));
    }
}
