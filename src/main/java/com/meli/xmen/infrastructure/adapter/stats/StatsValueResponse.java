/* (C)2023 */
package com.meli.xmen.infrastructure.adapter.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class StatsValueResponse {

    private Integer countMutantDna;

    private Integer countHumanDna;

    private Double ratio;
}
