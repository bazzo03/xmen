/* (C)2023 */
package com.meli.xmen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.meli.xmen.*")
public class XmenApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmenApplication.class, args);
    }
}
