/* (C)2023 */
package com.meli.xmen.infrastructure.out.repository.dna;

import jakarta.persistence.*;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
@AllArgsConstructor
public class DnaData {

    public DnaData(List<String> dnaList, boolean result) {
        this.dnaList = dnaList;
        this.result = result;
    }

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column private List<String> dnaList;

    @Column private boolean result;
}
