package com.gogoasa.c.data.repository;

import com.gogoasa.c.data.model.Reservation;
import com.gogoasa.c.data.model.User;
import com.gogoasa.c.data.model.dto.FavouriteStayDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserUsername(String username);

    @Query("select r from Reservation r where r.user.username = ?1 and r.checkIn = ?2 and r.checkOut = ?3")
    Reservation findReservationByUserAndCheckInAndCheckOut(String user, String checkIn, String checkOut);
}
