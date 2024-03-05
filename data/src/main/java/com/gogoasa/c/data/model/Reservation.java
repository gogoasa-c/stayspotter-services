package com.gogoasa.c.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Reservation {
    @Id
    private Long id;
    private Date checkin;
    private Date checkout;
    @ManyToOne
    private User user;
    private Boolean isPast;
}
