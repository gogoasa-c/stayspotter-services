package com.gogoasa.c.core.controller;

import com.gogoasa.c.core.model.Stay;
import com.gogoasa.c.core.model.dto.AvailabilityRequestDto;
import com.gogoasa.c.core.model.dto.AvailabilityResponseDto;
import com.gogoasa.c.core.model.dto.StayRequestDto;
import com.gogoasa.c.core.model.dto.StayResponseDto;
import com.gogoasa.c.core.service.StayService;
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

    private StayService stayService;

    @PostMapping
    public ResponseEntity<List<StayResponseDto>> findStays(@RequestBody StayRequestDto stayRequestDto) {
        log.info("Received request to find stays...");
        log.debug("Request: {}", stayRequestDto);

        List<StayResponseDto> stays = stayService.findStays(stayRequestDto);
        log.debug("Response: {}", stays);

        return ResponseEntity.ok(stays);
    }

    @GetMapping("/favourite")
    public ResponseEntity<List<StayResponseDto>> getFavourites(
        @RequestHeader("Authorization") String bearerToken
    ) {
        log.info("Received request to get favourites...");
        log.debug("Request: bearerToken = {}", bearerToken);

        List<StayResponseDto> favourites = stayService.getFavourites();
        log.debug("Response: {}", favourites);

        return ResponseEntity.ok(favourites);
    }

    @PostMapping("/favourite")
    public ResponseEntity<Void> saveStayToFavourites(@RequestBody Stay stay) {
        log.info("Received request to save stay to favourites...");
        log.debug("Request: {}", stay);

        stayService.saveStayToFavourites(stay);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/favourite/{id}")
    public ResponseEntity<Void> deleteStayFromFavourites(@PathVariable("id") Long reservationId) {
        log.info("Received request to delete stay from favourites...");
        log.debug("Request: {}", reservationId);

        stayService.deleteStayFromFavourites(reservationId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/availability")
    public ResponseEntity<AvailabilityResponseDto> checkAvailability(@RequestBody AvailabilityRequestDto requestDto) {
        log.info("Received request to check availability...");
        log.debug("Request: {}", requestDto);

        return ResponseEntity.ok(stayService.checkAvailability(requestDto));
    }
}
