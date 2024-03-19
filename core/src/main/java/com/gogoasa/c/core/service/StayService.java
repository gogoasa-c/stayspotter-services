package com.gogoasa.c.core.service;

import com.gogoasa.c.core.model.Stay;
import com.gogoasa.c.core.model.dto.StayRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class StayService {
    private RestTemplate restTemplate;

    public Stay findStays(StayRequestDto stayRequestDto) {
        return restTemplate.postForObject("http://scraper/stay/", stayRequestDto, Stay.class);
    }

    public void saveStayToFavourites(Stay stay) {
        Boolean successfullySaved = restTemplate
            .postForObject("http://data/favourite/", stay, Boolean.class);

        if (!Boolean.TRUE.equals(successfullySaved)) {
            throw new IllegalArgumentException("Failed to save stay to favourites!");
        }
    }
}
