/* (C)2023 */
package com.meli.xmen.infrastructure.in.mapper.dna;

import com.meli.xmen.domain.entity.DnaEntity;
import com.meli.xmen.infrastructure.in.adapter.dna.DnaRequest;
import org.springframework.stereotype.Service;

@Service
public class DomainDnaConverter {

    public DnaEntity convertFromRequestToDomainEntity(DnaRequest request) {
        return new DnaEntity(request.getDna());
    }

    public DnaRequest convertFromEntityToDomainRequest(DnaEntity entity) {
        return new DnaRequest(entity.getDnaList());
    }
}
