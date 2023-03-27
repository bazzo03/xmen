/* (C)2023 */
package com.meli.xmen.infrastructure.out.mapper.dna;

import com.meli.xmen.domain.entity.DnaEntity;
import com.meli.xmen.infrastructure.out.repository.dna.DnaData;
import org.springframework.stereotype.Service;

@Service
public class InfrastructureDnaConverter {

    public DnaData convertFromDomainEntityToRepositoryEntity(
            DnaEntity domainDnaEntity, Boolean result) {
        return new DnaData(domainDnaEntity.getDnaList(), result);
    }

    public DnaEntity convertFromRepositoryEntityToDomainEntity(DnaData dnaData) {
        return new DnaEntity(dnaData.getDnaList());
    }
}
