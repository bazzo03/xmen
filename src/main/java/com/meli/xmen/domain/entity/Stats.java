/* (C)2023 */
package com.meli.xmen.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Stats {

    private Integer countMutantDna;

    private Integer countHumanDna;

    private Double ratio;
}
