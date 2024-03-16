package com.gogoasa.c.core.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class StayService {
   private RestTemplate restTemplate;

   public void findStays() {

   }
}
