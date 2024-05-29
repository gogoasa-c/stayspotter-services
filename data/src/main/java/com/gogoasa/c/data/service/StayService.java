package com.gogoasa.c.data.service;

import com.gogoasa.c.data.model.Reservation;
import com.gogoasa.c.data.model.Stay;
import com.gogoasa.c.data.model.User;
import com.gogoasa.c.data.model.dto.FavouriteStayDto;
import com.gogoasa.c.data.repository.ReservationRepository;
import com.gogoasa.c.data.repository.StayRepository;
import com.gogoasa.c.data.util.Helper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
            .adults(stay.getAdults())
            .rooms(stay.getRooms())
            .x(stay.getX())
            .y(stay.getY())
            .build();

        stayRepository.save(stayToBeSaved);
    }

    public void increaseNumberOfSearches(String username) {

    }

    public FavouriteStayDto[] getFavouriteStays(String username) {

        List<Reservation> reservationList = reservationRepository.findByUserUsername(username);

        List<Stay> stayList = stayRepository.findByReservationId(reservationList.stream()
            .map(Reservation::getId)
            .toList());

        return stayList.stream()
            .map(stay -> FavouriteStayDto.builder()
                .city(stay.getCity())
                .link(stay.getLink())
                .name(stay.getName())
                .photoUrl(stay.getPhotoUrl())
                .price(stay.getPrice())
                .x(stay.getX())
                .y(stay.getY())
                .adults(stay.getAdults())
                .rooms(stay.getRooms())
                .checkIn(stay.getReservation().getCheckIn())
                .checkOut(stay.getReservation().getCheckOut())
                .username(stay.getReservation().getUser().getUsername())
                .build())
            .toArray(FavouriteStayDto[]::new);
    }
}
