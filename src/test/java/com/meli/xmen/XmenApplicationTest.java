/* (C)2023 */
package com.meli.xmen;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.meli.xmen.infrastructure.in.adapter.dna.DnaController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XmenApplicationTest {

    @Autowired private DnaController controller;

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }
}
