package com.gogoasa.c.data.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    @Column(length = 1000, columnDefinition = "TEXT")
    private String link;
    @Column(length = 1000, columnDefinition = "TEXT")
    private String photoUrl;
    private String name;
    private Float price;
    @OneToOne
    private Reservation reservation;
    private Float x;
    private Float y;
    private Integer adults;
    private Integer rooms;
}
