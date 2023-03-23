/* (C)2023 */
package com.meli.xmen.infrastructure.adapter.mutant;

import static org.springframework.http.HttpStatus.OK;

import com.meli.xmen.infrastructure.handler.mutant.MutantDnaHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/mutants")
public class MutantDnaController {

    @Autowired private MutantDnaHandler handler;

    @PostMapping
    public ResponseEntity<DnaResponse> findIsMutant(@RequestBody DnaRequest dto) {

        log.info("Request to validate if blood is from mutant {}", dto);
        return handler.findIsMutant(dto)
                .fold(
                        left ->
                                new ResponseEntity<>(
                                        new DnaResponse(left.getMessage(), null),
                                        HttpStatus.valueOf(left.getCode())),
                        right -> new ResponseEntity<>(new DnaResponse(null, right), OK));
    }
}
