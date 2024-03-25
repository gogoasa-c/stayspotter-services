package com.gogoasa.c.core.service;

import com.gogoasa.c.core.model.Stay;
import com.gogoasa.c.core.model.dto.StayRequestDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class StayService {
    private final RestTemplate restTemplate;

    @Value("${data-service.url:http://localhost:8087}")
    private String dataServiceUrl;

    @Value("${scraper-service.url:http://localhost:8090}")
    private String scraperServiceUrl;
    public Stay findStays(StayRequestDto stayRequestDto) {
//        return restTemplate.postForObject("http://scraper/stay/", stayRequestDto, Stay.class);
        return restTemplate
            .postForObject("%s/stay/".formatted(scraperServiceUrl), stayRequestDto, Stay.class);
    }

    public void saveStayToFavourites(Stay stay) {
//        Boolean successfullySaved = restTemplate
//            .postForObject("http://data/stay/favourite/", stay, Boolean.class);

        Boolean successfullySaved = restTemplate
            .postForObject("%s/stay/favourite/".formatted(dataServiceUrl), stay, Boolean.class);

        if (!Boolean.TRUE.equals(successfullySaved)) {
            throw new IllegalArgumentException("Failed to save stay to favourites!");
        }
    }
}
