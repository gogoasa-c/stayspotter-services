package com.gogoasa.c.data.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String checkIn;
    private String checkOut;
    @ManyToOne
    private User user;
    private Boolean isPast;
}
