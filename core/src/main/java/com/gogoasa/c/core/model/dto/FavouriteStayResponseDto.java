package com.gogoasa.c.core.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class FavouriteStayResponseDto {
    private String city;
    private String link;
    private String name;
    private String photo;
    private String price;
    private Integer adults;
    private Integer rooms;
    private Float x;
    private Float y;
    private Date checkIn;
    private Date checkOut;
}
