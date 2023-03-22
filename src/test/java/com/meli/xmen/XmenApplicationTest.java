/* (C)2023 */
package com.meli.xmen;

import com.meli.xmen.infrastructure.adapter.mutant.MutantDnaController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class XmenApplicationTest {

    @Autowired
    private MutantDnaController controller;

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }
}
