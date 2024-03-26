package com.gogoasa.c.core.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StayRequestDto {
    private String city;
    private Integer adults;
    private Integer rooms;
    private String checkIn;
    private String checkOut;
}
