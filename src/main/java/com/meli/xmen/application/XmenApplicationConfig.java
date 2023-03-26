/* (C)2023 */
package com.meli.xmen.application;

import com.meli.xmen.domain.usecase.dna.cell.CellService;
import com.meli.xmen.domain.usecase.dna.sequence.DiagonalSequenceService;
import com.meli.xmen.domain.usecase.dna.sequence.HorizontalSequenceService;
import com.meli.xmen.domain.usecase.dna.sequence.ReverseDiagonalService;
import com.meli.xmen.domain.usecase.dna.sequence.VerticalSequenceService;
import com.meli.xmen.domain.usecase.stats.StatsService;
import com.meli.xmen.infrastructure.out.mapper.dna.InfrastructureDnaConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmenApplicationConfig {

    @Bean
    public InfrastructureDnaConverter createInfrastructureDnaConverter() {
        return new InfrastructureDnaConverter();
    }

    @Bean
    public StatsService createStatsService() {
        return new StatsService();
    }

    @Bean
    public CellService createCellService() {
        return new CellService();
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
