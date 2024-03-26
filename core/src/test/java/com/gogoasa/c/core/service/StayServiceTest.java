package com.gogoasa.c.core.service;

import com.gogoasa.c.core.model.Stay;
import com.gogoasa.c.core.model.dto.StayResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class StayServiceTest {
    private StayService stayService;
    @Mock
    private RestTemplate restTemplate;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        stayService = new StayService(restTemplate);
    }

    @AfterEach
    void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    void findStaysSuccessfully() {
        StayResponseDto stay1 = new StayResponseDto();
        stay1.setName("Hotel");
        stay1.setPrice("100");
        stay1.setPhoto("http://photo.com");
        stay1.setX(1F);
        stay1.setY(1F);

        StayResponseDto stay2 = new StayResponseDto();
        stay2.setName("Hotel2");
        stay2.setPrice("200");
        stay2.setPhoto("http://photo2.com");
        stay2.setX(2F);
        stay2.setY(2F);

        StayResponseDto[] staysToBeReturned = {stay1, stay2};

        when(restTemplate.postForObject(anyString(), any(), eq(StayResponseDto[].class)))
            .thenReturn(staysToBeReturned);

        List<StayResponseDto> stayResponseDtoList = stayService.findStays(null);

        assertEquals(2, stayResponseDtoList.size());

        assertEquals("Hotel", stayResponseDtoList.get(0).getName());
        assertEquals("100", stayResponseDtoList.get(0).getPrice());
        assertEquals("http://photo.com", stayResponseDtoList.get(0).getPhoto());
        assertEquals(1F, stayResponseDtoList.get(0).getX());
        assertEquals(1F, stayResponseDtoList.get(0).getY());

        assertEquals("Hotel2", stayResponseDtoList.get(1).getName());
        assertEquals("200", stayResponseDtoList.get(1).getPrice());
        assertEquals("http://photo2.com", stayResponseDtoList.get(1).getPhoto());
        assertEquals(2F, stayResponseDtoList.get(1).getX());
        assertEquals(2F, stayResponseDtoList.get(1).getY());

    }

    @Test
    void saveStayToFavouritesSuccessfully() {
        when(restTemplate.postForObject(anyString(), any(), eq(Boolean.class)))
            .thenReturn(true);

        assertDoesNotThrow(() -> stayService.saveStayToFavourites(new Stay()));
    }

    @Test
    void saveStayToFavouritesUnsuccessfully() {
        when(restTemplate.postForObject(anyString(), any(), eq(Boolean.class)))
            .thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> stayService.saveStayToFavourites(new Stay()));
    }
}