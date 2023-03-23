/* (C)2023 */
package com.meli.xmen.infrastructure.mapper.mutant;

import com.meli.xmen.domain.entity.Dna;
import com.meli.xmen.infrastructure.adapter.mutant.DnaRequest;
import org.springframework.stereotype.Service;

@Service
public class MutantConverter {

    public Dna convertFromRequestToEntity(DnaRequest request) {
        return new Dna(request.getDna());
    }
}
