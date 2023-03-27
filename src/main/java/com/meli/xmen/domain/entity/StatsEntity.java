/* (C)2023 */
package com.meli.xmen.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class StatsEntity {

    private int countMutantDna;

    private int countHumanDna;

    private double ratio;
}
