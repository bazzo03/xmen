/* (C)2023 */
package com.meli.xmen.domain.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class DnaEntity {

    private List<String> dnaList;
}
