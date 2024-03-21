package com.gogoasa.c.core.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StayRequestDto {
    private String city;
    private Integer adults;
    private Integer rooms;
    private Date checkIn;
    private Date checkOut;
}
