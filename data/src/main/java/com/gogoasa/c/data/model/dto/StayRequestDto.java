package com.gogoasa.c.data.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StayRequestDto {
    private String name;
    private String photoUrl;
    private Float x, y;
    private Float price;
    private Date checkIn;
    private Date checkOut;
//    private String foundOn; // booking, trivago, airbnb
}
