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
public class Stats {
    @Id
    private int id;
    @OneToOne
    private User user;
    private int numberOfSearches;
}
