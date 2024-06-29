package com.gogoasa.c.data.service;

import com.gogoasa.c.data.model.Reservation;
import com.gogoasa.c.data.model.Stay;
import com.gogoasa.c.data.model.User;
import com.gogoasa.c.data.model.dto.FavouriteStayDto;
import com.gogoasa.c.data.repository.ReservationRepository;
import com.gogoasa.c.data.repository.StayRepository;
import com.gogoasa.c.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class StayService {

    private final UserService userService;
    private final StayRepository stayRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

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

    public FavouriteStayDto[] getFavouriteStays(String username) {

        List<Reservation> reservationList = reservationRepository.findByUserUsername(username);

        List<Stay> stayList = stayRepository.findByReservationId(reservationList.stream()
            .map(Reservation::getId)
            .toList());

        return stayList.stream()
            .map(stay -> FavouriteStayDto.builder()
                .reservationId(stay.getReservation().getId())
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

    public void deleteStayFromFavourites(Long reservationId, String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found!");
        }

        stayRepository.findByReservationId(List.of(reservationId))
            .stream()
            .filter(stay -> stay.getReservation().getUser().getUsername().equals(user.get().getUsername()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Stay not found!"));

        stayRepository.deleteByReservationId(reservationId);
        reservationRepository.deleteById(reservationId);
    }
}
