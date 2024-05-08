package com.gogoasa.c.core.model.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class StayRequestDto {
    private String city;
    private Integer adults;
    private Integer rooms;
    private String checkIn;
    private String checkOut;
    private String priceRangeStart;
    private String priceRangeEnd;
}
