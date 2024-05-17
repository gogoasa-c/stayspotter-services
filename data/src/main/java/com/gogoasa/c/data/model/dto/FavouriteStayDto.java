package com.gogoasa.c.data.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
public class FavouriteStayDto {
    private String city;
    private String link;
    private String name;
    private String photoUrl;
    private Float price;
    private Float x;
    private Float y;
    private Integer adults;
    private Integer rooms;
    private Date checkIn;
    private Date checkOut;
    private String username;
}
