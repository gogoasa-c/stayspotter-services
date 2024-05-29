package com.gogoasa.c.core.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StayResponseDto {
    private String city;
    private String link;
    private String name;
    private String photoUrl;
    private String price;
    private Float x;
    private Float y;
    private Integer adults;
    private Integer rooms;
    private String checkIn;
    private String checkOut;
}
