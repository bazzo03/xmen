/* (C)2023 */
package com.meli.xmen.infrastructure.out.repository.dna;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaH2Port extends CrudRepository<DnaData, Integer> {}
