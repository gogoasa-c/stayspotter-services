package com.gogoasa.c.data.controller;

import com.gogoasa.c.data.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    @GetMapping
    public ResponseEntity<Void> increaseCheckedOutStays(@RequestParam("username") String username) {
        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        statsService.increaseNumberOfSearches(username);
        return ResponseEntity.ok().build();
    }
}
