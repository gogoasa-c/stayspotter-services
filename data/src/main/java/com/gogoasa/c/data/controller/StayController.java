package com.gogoasa.c.data.controller;

import com.gogoasa.c.data.model.dto.StayRequestDto;
import com.gogoasa.c.data.repository.StayRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stay")
@AllArgsConstructor
@Slf4j
public class StayController {

    private StayRepository stayRepository;

    public Boolean saveStayToFavourites(StayRequestDto stay) {
        log.info("Saving stay to favourites...");

//        stayRepository.save(stay);

        return Boolean.TRUE;
    }
}
