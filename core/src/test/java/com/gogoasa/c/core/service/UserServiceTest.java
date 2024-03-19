package com.gogoasa.c.core.service;

import com.gogoasa.c.core.model.User;
import com.gogoasa.c.core.model.dto.UserRequestDto;
import com.gogoasa.c.core.security.JwtProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserService userService;
    @Mock
    private JwtProvider jwtProvider;
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(jwtProvider, restTemplate);
    }

    @AfterEach
    public void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void loginSuccessfully() {
        when(restTemplate.postForObject(anyString(), any(), eq(Boolean.class)))
            .thenReturn(true);
        when(jwtProvider.generateToken(anyString(), anyLong(), any()))
            .thenReturn("token");

        String token = userService.login(new UserRequestDto("user", "password"));

        assertEquals("token", token);
    }

    @Test
    public void loginUnsuccessfully() {
        when(restTemplate.postForObject(anyString(), any(), eq(Boolean.class)))
            .thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> userService.login(new UserRequestDto("user", "password")));
    }

    @Test
    public void createUserSuccessfully() {
        final String username = "user";
        final String password = "password";

        User initialUser = new User();
        initialUser.setEmail("mail@mail.com");
        initialUser.setUsername(username);
        initialUser.setPassword(password);

        when(restTemplate.postForObject(any(String.class), any(User.class), eq(User.class)))
            .thenReturn(initialUser);

        User user = userService.createUser(initialUser);

        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }

}