package com.gogoasa.c.core.service;

import com.gogoasa.c.core.model.Stay;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

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
        Stay stayToBeReturned = new Stay();
        stayToBeReturned.setName("Hotel");
        stayToBeReturned.setPrice(100F);
        stayToBeReturned.setPhotoUrl("http://photo.com");
        stayToBeReturned.setX(1F);
        stayToBeReturned.setY(1F);

        when(restTemplate.postForObject(anyString(), any(), eq(Stay.class)))
            .thenReturn(stayToBeReturned);

        Stay stay = stayService.findStays(null);

        assertNotNull(stay);
        assertEquals("Hotel", stay.getName());
        assertEquals(100F, stay.getPrice());
        assertEquals("http://photo.com", stay.getPhotoUrl());
        assertEquals(1F, stay.getX());
        assertEquals(1F, stay.getY());
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