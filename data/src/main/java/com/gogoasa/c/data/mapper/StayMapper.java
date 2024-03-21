package com.gogoasa.c.data.mapper;

import com.gogoasa.c.data.model.Stay;
import com.gogoasa.c.data.model.dto.StayRequestDto;

public class StayMapper {
    public static void map(StayRequestDto stayRequestDto) {
        Stay stay = new Stay();

        stay.setName(stayRequestDto.getName());
        stay.setPrice(stayRequestDto.getPrice());
    }
}
