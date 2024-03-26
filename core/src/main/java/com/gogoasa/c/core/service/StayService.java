package com.gogoasa.c.core.service;

import com.gogoasa.c.core.model.Stay;
import com.gogoasa.c.core.model.dto.StayRequestDto;
import com.gogoasa.c.core.model.dto.StayResponseDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StayService {
    private final RestTemplate restTemplate;

    @Value("${data-service.url:http://localhost:8087}")
    private String dataServiceUrl;

    @Value("${scraper-service.url:http://localhost:8090}")
    private String scraperServiceUrl;

    public List<StayResponseDto> findStays(StayRequestDto stayRequestDto) {
//        return restTemplate.postForObject("http://scraper/stay/", stayRequestDto, Stay.class);
        StayResponseDto[] stayArray = restTemplate
            .postForObject("%s/stays".formatted(scraperServiceUrl), stayRequestDto, StayResponseDto[].class);

        return Arrays.asList(stayArray);
    }

    public void saveStayToFavourites(Stay stay) {
//        Boolean successfullySaved = restTemplate
//            .postForObject("http://data/stay/favourite/", stay, Boolean.class);

        Boolean successfullySaved = restTemplate
            .postForObject("%s/stay/favourite".formatted(dataServiceUrl), stay, Boolean.class);

        if (!Boolean.TRUE.equals(successfullySaved)) {
            throw new IllegalArgumentException("Failed to save stay to favourites!");
        }
    }
}
