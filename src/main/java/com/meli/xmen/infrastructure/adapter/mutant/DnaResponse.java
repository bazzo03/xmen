/* (C)2023 */
package com.meli.xmen.infrastructure.adapter.mutant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class DnaResponse {

    private String message;

    private Boolean isMutant;
}
