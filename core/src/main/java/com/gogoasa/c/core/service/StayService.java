package com.gogoasa.c.core.service;

import com.gogoasa.c.core.model.Stay;
import com.gogoasa.c.core.model.dto.StayRequestDto;
import com.gogoasa.c.core.model.dto.StayResponseDto;
import com.gogoasa.c.core.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
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
        StayResponseDto[] stayArray = restTemplate
            .postForObject("%s/stays".formatted(scraperServiceUrl), stayRequestDto, StayResponseDto[].class);

        return stayArray != null ? Arrays.asList(stayArray) : List.of();
    }

    public void saveStayToFavourites(Stay stay) {
        stay.setUsername(
            SecurityContextHolder.getContext().getAuthentication().getName()
        );

        Boolean successfullySaved = restTemplate
            .postForObject("%s/stay/favourite".formatted(dataServiceUrl), stay, Boolean.class);

        if (!Boolean.TRUE.equals(successfullySaved)) {
            throw new IllegalArgumentException("Failed to save stay to favourites!");
        }
    }

    public List<StayResponseDto> getFavourites() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        StayResponseDto[] stayArray = restTemplate
            .getForObject("%s/stay/favourite?username=%s".formatted(dataServiceUrl, username), StayResponseDto[].class);

        return stayArray != null ? Arrays.asList(stayArray) : List.of();
   }
}
