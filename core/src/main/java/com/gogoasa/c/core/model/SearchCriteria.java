package com.gogoasa.c.core.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SearchCriteria {
    private String city;
    private Integer adults;
    private Integer children;
    private Integer rooms;
    private Date checkIn;
    private Date checkOut;
    private List<Objective> objectiveList;
}
