package com.gogoasa.c.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Stay {
    @Id
    private Long id;
    private String name;
    private String address;
    private Double price;
    @OneToOne
    private Reservation reservation;
}
