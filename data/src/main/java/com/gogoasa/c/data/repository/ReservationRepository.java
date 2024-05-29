package com.gogoasa.c.data.repository;

import com.gogoasa.c.data.model.Reservation;
import com.gogoasa.c.data.model.dto.FavouriteStayDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserUsername(String username);
}
