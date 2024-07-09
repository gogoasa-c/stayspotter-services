package com.gogoasa.c.core.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityRequestDto implements Serializable {
    private String stayUrl;
    private Integer initialPrice;
}
