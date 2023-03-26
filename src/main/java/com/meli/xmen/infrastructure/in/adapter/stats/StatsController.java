/* (C)2023 */
package com.meli.xmen.infrastructure.in.adapter.stats;

import com.meli.xmen.infrastructure.in.handler.stats.StatsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/stats")
public class StatsController {

    @Autowired private StatsHandler handler;

    @GetMapping
    public ResponseEntity<StatsResponse> getStats() {
        return handler.getStats()
                .fold(
                        left ->
                                new ResponseEntity<>(
                                        new StatsResponse(
                                                left.getMessage(),
                                                new StatsValueResponse(0, 0, 0.0)),
                                        HttpStatusCode.valueOf(left.getCode())),
                        right ->
                                new ResponseEntity<>(
                                        new StatsResponse(null, right), HttpStatus.OK));
    }
}
