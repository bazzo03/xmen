package com.meli.xmen.application;

import com.meli.xmen.domain.usecase.cell.CellService;
import com.meli.xmen.domain.usecase.dna.DnaService;
import com.meli.xmen.domain.usecase.sequence.DiagonalSequenceService;
import com.meli.xmen.domain.usecase.sequence.HorizontalSequenceService;
import com.meli.xmen.domain.usecase.sequence.ReverseDiagonalService;
import com.meli.xmen.domain.usecase.sequence.VerticalSequenceService;
import com.meli.xmen.domain.usecase.stats.StatsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(XmenApplicationConfig.class)
class XmenApplicationConfigTest {

    @Autowired
    private StatsService createStatsService;

    @Autowired
    private CellService createCellService;

    @Autowired
    private DnaService createDnaService;

    @Autowired
    private DiagonalSequenceService createDiagonalService;

    @Autowired
    private VerticalSequenceService createVerticalSequenceService;

    @Autowired
    private HorizontalSequenceService createHorizontalSequenceService;

    @Autowired
    private ReverseDiagonalService createReverseDiagonalService;

    @Test
    void contextLoads() {
        assertNotNull(createStatsService);
        assertNotNull(createCellService);
        assertNotNull(createDnaService);
        assertNotNull(createDiagonalService);
        assertNotNull(createVerticalSequenceService);
        assertNotNull(createHorizontalSequenceService);
        assertNotNull(createReverseDiagonalService);
    }
}
