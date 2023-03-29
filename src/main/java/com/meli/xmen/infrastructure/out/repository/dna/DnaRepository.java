/* (C)2023 */
package com.meli.xmen.infrastructure.out.repository.dna;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DnaRepository {

    @Autowired DnaH2Port h2Repository;

    public DnaData saveDna(DnaData entity) {
        return h2Repository.save(entity);
    }
}
