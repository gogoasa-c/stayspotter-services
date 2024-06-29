package com.gogoasa.c.data.model.dto;

import lombok.*;

@Builder
@Data
public class FavouriteStayDto {
    private Long reservationId;
    private String city;
    private String link;
    private String name;
    private String photoUrl;
    private Float price;
    private Float x;
    private Float y;
    private Integer adults;
    private Integer rooms;
    private String checkIn;
    private String checkOut;
    private String username;
}
