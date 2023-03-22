/* (C)2023 */
package com.meli.xmen.application;

import com.meli.xmen.domain.usecase.cell.CellService;
import com.meli.xmen.domain.usecase.dna.DnaService;
import com.meli.xmen.domain.usecase.sequence.DiagonalSequenceService;
import com.meli.xmen.domain.usecase.sequence.HorizontalSequenceService;
import com.meli.xmen.domain.usecase.sequence.ReverseDiagonalService;
import com.meli.xmen.domain.usecase.sequence.VerticalSequenceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmenApplicationConfig {

    @Bean
    public CellService createCellService() {
        return new CellService();
    }

    @Bean
    public DnaService createDnaService() {
        return new DnaService();
    }

    @Bean
    public DiagonalSequenceService createDiagonalService() {
        return new DiagonalSequenceService();
    }

    @Bean
    public VerticalSequenceService createVerticalSequenceService() {
        return new VerticalSequenceService();
    }

    @Bean
    public HorizontalSequenceService createHorizontalSequenceService() {
        return new HorizontalSequenceService();
    }

    @Bean
    public ReverseDiagonalService createReverseDiagonalService() {
        return new ReverseDiagonalService();
    }
}
