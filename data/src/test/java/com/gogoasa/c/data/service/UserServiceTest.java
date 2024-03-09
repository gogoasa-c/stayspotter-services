package com.gogoasa.c.data.service;

import com.gogoasa.c.data.model.User;
import com.gogoasa.c.data.model.dto.UserCreationDto;
import com.gogoasa.c.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class UserServiceTest {

    UserService userService;
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @AfterEach
    public void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void createUserSuccessfully() {
        String expectedPassword = "encodedPassword";

        UserCreationDto user = new UserCreationDto("username", "email", "password");

        when(userRepository.findById(user.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn(expectedPassword);
        when(userRepository.save(any(User.class))).thenReturn(null);

        UserCreationDto result = userService.createUser(user);

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(expectedPassword, result.getPassword());
    }

    @Test
    public void createUserFailed() {
        UserCreationDto user = new UserCreationDto("username", "email", "password");

        when(userRepository.findById(user.getUsername())).thenReturn(Optional.of(new User()));

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(user));
    }

}