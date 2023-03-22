/* (C)2023 */
package com.meli.xmen.domain.usecase;

import com.meli.xmen.domain.entity.Cell;

public class SequenceUtil {

    public static Integer findMutantSequenceArray(Cell[] array) {

        int totalSequences = 0;
        int sameCharactersCounter = 1;

        for (int i = 0; i < array.length - 1; i++) {

            if (array[i].getCurrentChar().equals(array[i + 1].getCurrentChar())) {
                sameCharactersCounter++;
            } else {
                sameCharactersCounter = 1;
            }
            if (sameCharactersCounter == 4) {
                totalSequences++;
                sameCharactersCounter = 1;
            }
        }
        return totalSequences;
    }
}
