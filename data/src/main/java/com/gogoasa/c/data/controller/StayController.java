package com.gogoasa.c.data.controller;

import com.gogoasa.c.data.model.Stay;
import com.gogoasa.c.data.service.StayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stay")
@AllArgsConstructor
@Slf4j
public class StayController {

    private final StayService stayService;

    @PostMapping("/favourite")
    public Boolean saveStayToFavourites(Stay stay) {
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
