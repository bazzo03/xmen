/* (C)2023 */
package com.meli.xmen.domain.usecase;

import com.meli.xmen.domain.entity.Cell;

public class SequenceUtil {

    private static Integer MAX_SAME_CHARACTERS = 4;

    private SequenceUtil() {}

    public static Integer findMutantSequenceArray(Cell[] array) {

        var totalSequences = 0;
        var sameCharactersCounter = 1;

        for (var i = 0; i < array.length - 1; i++) {

            if (array[i].getCurrentChar().equals(array[i + 1].getCurrentChar())) {
                sameCharactersCounter++;
            } else {
                sameCharactersCounter = 1;
            }
            if (sameCharactersCounter == MAX_SAME_CHARACTERS) {
                totalSequences++;
                sameCharactersCounter = 1;
            }
        }
        return totalSequences;
    }
}
