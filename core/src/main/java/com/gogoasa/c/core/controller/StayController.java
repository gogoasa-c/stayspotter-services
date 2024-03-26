package com.gogoasa.c.core.controller;

import com.gogoasa.c.core.model.Stay;
import com.gogoasa.c.core.model.dto.StayRequestDto;
import com.gogoasa.c.core.model.dto.StayResponseDto;
import com.gogoasa.c.core.service.StayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stay")
@AllArgsConstructor
@Slf4j
public class StayController {

    private StayService stayService;

    @PostMapping
    public ResponseEntity<List<StayResponseDto>> findStays(@RequestBody StayRequestDto stayRequestDto) {
        log.info("Received request to find stays...");
        log.debug("Request: {}", stayRequestDto);

        List<StayResponseDto> stays = stayService.findStays(stayRequestDto);
        log.debug("Response: {}", stays);

        return ResponseEntity.ok(stays);
    }

    @PostMapping("/favourite")
    public ResponseEntity<Void> saveStayToFavourites(@RequestBody Stay stay) {
        log.info("Received request to save stay to favourites...");
        log.debug("Request: {}", stay);

        stayService.saveStayToFavourites(stay);
        return ResponseEntity.ok().build();
    }
}
