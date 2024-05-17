package com.gogoasa.c.data.service;

import com.gogoasa.c.data.model.Reservation;
import com.gogoasa.c.data.model.Stay;
import com.gogoasa.c.data.model.User;
import com.gogoasa.c.data.model.dto.FavouriteStayDto;
import com.gogoasa.c.data.repository.ReservationRepository;
import com.gogoasa.c.data.repository.StayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StayService {

    private final UserService userService;
    private final StayRepository stayRepository;
    private final ReservationRepository reservationRepository;

    public void saveStayToFavourites(FavouriteStayDto stay) {
        User user = userService.getFullUser(stay.getUsername());

        Reservation reservation = Reservation.builder()
            .checkIn(stay.getCheckIn())
            .checkOut(stay.getCheckOut())
            .user(user)
            .isPast(false)
            .build();

        reservationRepository.save(reservation);

        Stay stayToBeSaved = Stay.builder()
            .city(stay.getCity())
            .link(stay.getLink())
            .photoUrl(stay.getPhotoUrl())
            .name(stay.getName())
            .price(stay.getPrice())
            .reservation(reservation)
            .build();

        stayRepository.save(stayToBeSaved);
    }

    public void increaseNumberOfSearches(String username) {

    }
}
