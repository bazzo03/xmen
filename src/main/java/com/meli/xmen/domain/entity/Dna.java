/* (C)2023 */
package com.meli.xmen.domain.entity;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder(setterPrefix = "with")
public class Dna {

    private List<String> dna;
}
