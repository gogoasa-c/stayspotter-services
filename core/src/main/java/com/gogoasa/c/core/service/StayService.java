package com.gogoasa.c.core.service;

import com.gogoasa.c.core.model.Stay;
import com.gogoasa.c.core.model.dto.AvailabilityRequestDto;
import com.gogoasa.c.core.model.dto.AvailabilityResponseDto;
import com.gogoasa.c.core.model.dto.StayRequestDto;
import com.gogoasa.c.core.model.dto.StayResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
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

    public void deleteStayFromFavourites(Long reservationId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        restTemplate.delete("%s/stay/favourite/%d?username=%s".formatted(dataServiceUrl,
            reservationId, username));

    }

    public AvailabilityResponseDto checkAvailability(AvailabilityRequestDto availabilityRequestDto) {
        if (availabilityRequestDto.getStayUrl() == null || availabilityRequestDto.getStayUrl().isBlank()) {
            throw new IllegalArgumentException("Stay URL is either null or empty!");
        }

        return restTemplate.postForObject("%s/stays/availability".formatted(scraperServiceUrl),
            availabilityRequestDto, AvailabilityResponseDto.class);
    }
}
