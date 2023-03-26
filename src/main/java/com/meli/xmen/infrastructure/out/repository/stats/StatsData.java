/* (C)2023 */
package com.meli.xmen.infrastructure.out.repository.stats;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class StatsData {

    public StatsData(
            int countMutantDna, int countHumanDna, Double ratio, OffsetDateTime dateTime) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        this.ratio = ratio;
        this.dateTime = dateTime;
    }

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column private int countMutantDna;

    @Column private int countHumanDna;

    @Column private double ratio;

    @Column private OffsetDateTime dateTime;
}
