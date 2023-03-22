/* (C)2023 */
package com.meli.xmen.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {

    private Integer code;

    private String message;
}
