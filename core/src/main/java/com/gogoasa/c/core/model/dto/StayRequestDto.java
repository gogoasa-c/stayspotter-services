package com.gogoasa.c.core.model.dto;

import lombok.Data;

@Data
public class StayRequestDto {
    private String city;
    private Integer adults;
    private Integer rooms;
    private String checkIn;
    private String checkOut;
}
