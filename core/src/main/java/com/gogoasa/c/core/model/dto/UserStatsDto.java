package com.gogoasa.c.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserStatsDto {
    private String username;
    private Integer numberOfSearches;
    private Double topPercentage;
}
