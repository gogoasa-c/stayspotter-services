package com.gogoasa.c.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AvailabilityResponseDto implements Serializable {
    private Boolean available;
    private Boolean priceChanged;
}
