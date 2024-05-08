package com.gogoasa.c.data.repository;

import com.gogoasa.c.data.model.Stats;
import com.gogoasa.c.data.model.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StayRepository extends JpaRepository<Stay, Long> {
}
