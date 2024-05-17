package com.gogoasa.c.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date checkIn;
    private Date checkOut;
    @ManyToOne
    private User user;
    private Boolean isPast;
}
