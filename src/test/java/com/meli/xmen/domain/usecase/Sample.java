/* (C)2023 */
package com.meli.xmen.domain.usecase;

import java.util.List;

public class Sample {

    public static List<String> createMutantCharacterList() {
        return List.of(new String[] {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"});
    }

    public static List<String> createWrongMutantCharacterList() {
        return List.of(new String[] {"ATGXGA", "XAGTGC", "TXATGT", "AXAAGG", "CXCCTA", "TCACTX"});
    }

    public static List<String> createMutantWithErrorCharacterList() {
        return List.of(new String[] {"ATGCGA", "CAG", "TTATGT", "AGA", "CCCCTA", "TCACTG"});
    }

    public static char[][] createMutantMatrix() {
        char[][] matrix = new char[6][6];
        matrix[0][0] = 'A';
        matrix[0][1] = 'T';
        matrix[0][2] = 'G';
        matrix[0][3] = 'C';
        matrix[0][4] = 'G';
        matrix[0][5] = 'A';
        matrix[1][0] = 'C';
        matrix[1][1] = 'A';
        matrix[1][2] = 'G';
        matrix[1][3] = 'T';
        matrix[1][4] = 'G';
        matrix[1][5] = 'C';
        matrix[2][0] = 'T';
        matrix[2][1] = 'T';
        matrix[2][2] = 'A';
        matrix[2][3] = 'T';
        matrix[2][4] = 'G';
        matrix[2][5] = 'T';
        matrix[3][0] = 'A';
        matrix[3][1] = 'G';
        matrix[3][2] = 'A';
        matrix[3][3] = 'A';
        matrix[3][4] = 'G';
        matrix[3][5] = 'G';
        matrix[4][0] = 'C';
        matrix[4][1] = 'C';
        matrix[4][2] = 'C';
        matrix[4][3] = 'C';
        matrix[4][4] = 'T';
        matrix[4][5] = 'A';
        matrix[5][0] = 'T';
        matrix[5][1] = 'C';
        matrix[5][2] = 'A';
        matrix[5][3] = 'C';
        matrix[5][4] = 'T';
        matrix[5][5] = 'G';
        return matrix;
    }

    public static char[][] createMatrixWithErrors() {
        char[][] matrix = new char[6][6];
        matrix[0][0] = 'A';
        matrix[0][1] = 'T';
        matrix[0][2] = 'G';
        matrix[0][3] = 'C';
        matrix[0][4] = 'G';
        matrix[0][5] = 'A';
        matrix[1][0] = 'C';
        matrix[1][1] = 'A';
        matrix[1][2] = 'G';
        matrix[1][3] = 'T';
        matrix[1][4] = 'G';
        matrix[1][5] = 'C';
        matrix[2][0] = 'T';
        matrix[2][1] = 'T';
        matrix[2][2] = 'A';
        matrix[2][3] = 'T';
        matrix[2][4] = 'G';
        matrix[2][5] = 'T';
        matrix[3][0] = 'A';
        matrix[3][1] = 'G';
        matrix[3][2] = 'A';
        matrix[3][3] = 'A';
        matrix[3][4] = 'G';
        matrix[3][5] = 'G';
        matrix[4][0] = 'C';
        matrix[4][1] = 'C';
        matrix[4][2] = 'C';
        matrix[4][3] = 'C';
        matrix[4][4] = 'T';
        matrix[4][5] = 'A';
        matrix[5][0] = 'T';
        matrix[5][1] = 'C';
        matrix[5][2] = 'A';
        matrix[5][3] = 'C';
        matrix[5][4] = 'T';
        matrix[5][5] = 'G';
        return matrix;
    }

    public static char[][] createHumanMatrix() {
        char[][] matrix = new char[6][6];
        matrix[0][0] = 'A';
        matrix[0][1] = 'T';
        matrix[0][2] = 'G';
        matrix[0][3] = 'C';
        matrix[0][4] = 'G';
        matrix[0][5] = 'A';
        matrix[1][0] = 'C';
        matrix[1][1] = 'A';
        matrix[1][2] = 'G';
        matrix[1][3] = 'T';
        matrix[1][4] = 'G';
        matrix[1][5] = 'C';
        matrix[2][0] = 'T';
        matrix[2][1] = 'T';
        matrix[2][2] = 'A';
        matrix[2][3] = 'T';
        matrix[2][4] = 'T';
        matrix[2][5] = 'T';
        matrix[3][0] = 'A';
        matrix[3][1] = 'G';
        matrix[3][2] = 'A';
        matrix[3][3] = 'C';
        matrix[3][4] = 'G';
        matrix[3][5] = 'G';
        matrix[4][0] = 'G';
        matrix[4][1] = 'C';
        matrix[4][2] = 'G';
        matrix[4][3] = 'T';
        matrix[4][4] = 'C';
        matrix[4][5] = 'A';
        matrix[5][0] = 'T';
        matrix[5][1] = 'C';
        matrix[5][2] = 'A';
        matrix[5][3] = 'C';
        matrix[5][4] = 'T';
        matrix[5][5] = 'G';
        return matrix;
    }
}
