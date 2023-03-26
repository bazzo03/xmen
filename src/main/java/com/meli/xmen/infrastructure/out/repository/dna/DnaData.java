/* (C)2023 */
package com.meli.xmen.infrastructure.out.repository.dna;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class DnaData {

    public DnaData(List<String> dnaList, Boolean result) {
        this.dnaList = dnaList;
        this.result = result;
    }

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column private List<String> dnaList;

    @Column private Boolean result;
}
