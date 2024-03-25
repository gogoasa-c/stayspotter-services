package com.gogoasa.c.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class RoutingConfiguration {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user", r -> r.path("/user/**")
                .and().method(HttpMethod.POST)
                .and().method(HttpMethod.GET)
                .uri("lb://core"))
            .route("stay", r -> r.path("/stay/**")
                .and().method(HttpMethod.POST)
                .and().method(HttpMethod.GET)
                .uri("lb://core"))
//            .route("data", r -> r.path("/data/**")
//                .and().method(HttpMethod.POST)
//                .and().method(HttpMethod.GET)
//                .uri("lb://data"))
//            .route("scraper", r -> r.path("/scraper/**")
//                .and().method(HttpMethod.POST)
//                .and().method(HttpMethod.GET)
//                .uri("lb://scraper"))
            .build();
    }

}
