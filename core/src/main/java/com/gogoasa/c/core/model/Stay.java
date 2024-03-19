package com.gogoasa.c.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stay {
    private String name;
    private String photoUrl;
    private Float x, y;
    private Float price;
//    private String foundOn; // booking, trivago, airbnb
}
