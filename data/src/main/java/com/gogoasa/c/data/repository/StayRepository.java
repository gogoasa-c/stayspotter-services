package com.gogoasa.c.data.repository;

import com.gogoasa.c.data.model.Stats;
import com.gogoasa.c.data.model.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StayRepository extends JpaRepository<Stay, Long> {
    @Query("select s from Stay s where s.reservation.id in ?1")
    List<Stay> findByReservationId(List<Long> idList);
}
