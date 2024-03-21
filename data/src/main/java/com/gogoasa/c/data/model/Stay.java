package com.gogoasa.c.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Stay {
    @Id
    private Long id;
    private String name;
    private String address;
    private Float price;
    @OneToOne
    private Reservation reservation;
}
