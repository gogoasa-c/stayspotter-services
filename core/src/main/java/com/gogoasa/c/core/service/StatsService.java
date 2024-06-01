package com.gogoasa.c.core.service;


import com.gogoasa.c.core.model.dto.UserStatsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final RestTemplate restTemplate;
    @Value("${data-service.url:http://localhost:8087}")
    private String dataServiceUrl;

    public void increaseCheckedOutStays() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        restTemplate.getForObject("%s/stats?username=%s".formatted(dataServiceUrl, username), Void.class);
    }

    public UserStatsDto getUserStats() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return restTemplate.getForObject("%s/stats/user?username=%s".formatted(dataServiceUrl, username),
            UserStatsDto.class);
    }
}
