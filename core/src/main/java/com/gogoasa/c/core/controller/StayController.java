package com.gogoasa.c.core.controller;

import com.gogoasa.c.core.model.Stay;
import com.gogoasa.c.core.model.dto.StayRequestDto;
import com.gogoasa.c.core.service.StayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stay")
@AllArgsConstructor
@Slf4j
public class StayController {

    private StayService stayService;

    @PostMapping("/")
    public ResponseEntity<Stay> findStays(@RequestBody StayRequestDto stayRequestDto) {
        log.info("Searching for stays...");
        return ResponseEntity.ok(stayService.findStays(stayRequestDto));
    }

    @PostMapping("/favourite")
    public ResponseEntity<Void> saveStayToFavourites(@RequestBody Stay stay) {
        log.info("Saving stay to favourites...");
        stayService.saveStayToFavourites(stay);
        return ResponseEntity.ok().build();
    }
}
