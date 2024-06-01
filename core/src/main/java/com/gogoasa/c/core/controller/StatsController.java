package com.gogoasa.c.core.controller;

import com.gogoasa.c.core.service.StatsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@AllArgsConstructor
@Slf4j
public class StatsController {
    private final StatsService statsService;
    @GetMapping
    public ResponseEntity<Void> increaseCheckedOutStays() {
        log.info("Received request for increasing number of checked out stays...");

        statsService.increaseCheckedOutStays();
        return ResponseEntity.ok().build();
    }
}
