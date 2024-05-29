package com.gogoasa.c.data.controller;

import com.gogoasa.c.data.model.dto.FavouriteStayDto;
import com.gogoasa.c.data.service.StayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stay")
@AllArgsConstructor
@Slf4j
public class StayController {

    private final StayService stayService;

    @GetMapping("/favourite")
    public ResponseEntity<FavouriteStayDto[]> getFavouriteStays(@RequestParam("username") String username) {
        log.info("Received request to find stays...");
        log.debug("Request: {}", username);

        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        FavouriteStayDto[] stays = stayService.getFavouriteStays(username);
        log.debug("Response: {}", stays);

        return ResponseEntity.ok(stays);
    }

    @PostMapping("/favourite")
    public Boolean saveStayToFavourites(@RequestBody FavouriteStayDto stay) {
        log.info("Received request to save stay to favourites...");
        log.debug("Request: {}", stay);

        stayService.saveStayToFavourites(stay);

        return Boolean.TRUE;
    }

    public Boolean increaseNumberOfSearches(String username) {
        log.info("Received request to increase number of searches...");
        log.debug("Request: username = {}", username);

        stayService.increaseNumberOfSearches(username);

        return Boolean.TRUE;
    }
}
