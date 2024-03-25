package com.gogoasa.c.data.service;

import com.gogoasa.c.data.model.Stay;
import com.gogoasa.c.data.repository.StayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StayService {

    private final StayRepository stayRepository;

    public void saveStayToFavourites(Stay stay) {
        stayRepository.save(stay);
    }
}
