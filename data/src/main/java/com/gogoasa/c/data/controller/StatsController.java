package com.gogoasa.c.data.controller;

import com.gogoasa.c.data.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/stats/{username}")
    public ResponseEntity<Void> increaseNumberOfSearches(@PathVariable String username) {
        statsService.increaseNumberOfSearches(username);
        return ResponseEntity.ok().build();
    }
}
