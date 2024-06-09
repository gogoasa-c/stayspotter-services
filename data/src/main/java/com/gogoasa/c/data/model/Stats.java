package com.gogoasa.c.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Stats {
    @Id
    @SequenceGenerator(name = "stats_id_seq", sequenceName = "stats_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stats_id_seq")
    private Long id;
    @OneToOne
    private User user;
    private int numberOfSearches;
}
