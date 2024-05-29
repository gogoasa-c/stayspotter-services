package com.gogoasa.c.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stay {
    private String city;
    private String name;
    private String link;
    private String photoUrl;
    private Float x, y;
    private Float price;
    private Integer adults;
    private Integer rooms;
    private String checkIn;
    private String checkOut;
    private String username;
//    private String foundOn; // booking, trivago, airbnb
}
