/* (C)2023 */
package com.meli.xmen.domain.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CellEntity {
    private Character[][] dna;
    private Integer row;
    private Integer column;
    private Character previousChar;
    private Character currentChar;
    private Integer size;

    public CellEntity(Character[][] dna, Integer row, Integer column) {
        this.dna = dna;
        this.size = dna.length;
        this.row = row;
        this.column = column;
        this.currentChar = dna[row][column];
    }
}
