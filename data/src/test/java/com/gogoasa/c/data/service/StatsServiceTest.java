package com.gogoasa.c.data.service;

import com.gogoasa.c.data.model.Stats;
import com.gogoasa.c.data.model.User;
import com.gogoasa.c.data.repository.StatsRepository;
import com.gogoasa.c.data.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class StatsServiceTest {

    private StatsService statsService;

    @Mock
    private StatsRepository statsRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        statsService = new StatsService(statsRepository, userRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void increaseNumberOfSearchesSuccessfully() {

        Stats statsToReturn = spy(new Stats());
        statsToReturn.setNumberOfSearches(1);

        when(userRepository.findById("username")).thenReturn(Optional.of(new User()));
        when(statsRepository.findStatsByUser(any(User.class))).thenReturn(Optional.of(statsToReturn));
        when(statsRepository.save(any(Stats.class))).thenReturn(null);

        statsService.increaseNumberOfSearches("username");

        assertEquals(2, statsToReturn.getNumberOfSearches());
    }
}