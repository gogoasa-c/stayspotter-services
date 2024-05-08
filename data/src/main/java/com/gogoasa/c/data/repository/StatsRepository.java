package com.gogoasa.c.data.repository;

import com.gogoasa.c.data.model.Stats;
import com.gogoasa.c.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatsRepository extends JpaRepository<Stats, Long> {
    Optional<Stats> findStatsByUser(User user);
}
