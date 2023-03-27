/* (C)2023 */
package com.meli.xmen.domain.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CellEntity {
    private char[][] dna;
    private int row;
    private int column;
    private char previousChar;
    private char currentChar;
    private int size;

    public CellEntity(char[][] dna, int row, int column) {
        this.dna = dna;
        this.size = dna.length;
        this.row = row;
        this.column = column;
        this.currentChar = dna[row][column];
    }
}
