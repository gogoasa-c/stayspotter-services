package com.gogoasa.c.core.service;

import com.gogoasa.c.core.model.User;
import com.gogoasa.c.core.model.dto.UserRequestDto;
import com.gogoasa.c.core.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static com.gogoasa.c.core.utils.Constant.ROLE_USER;

@AllArgsConstructor
@Service
public class UserService {

    private JwtProvider jwtProvider;
    private RestTemplate restTemplate;

    public User createUser(User user) {
        return restTemplate
            .postForObject("http://data/user/", user, User.class);
    }

    public String login(UserRequestDto user) {
        boolean successfulLogin = Boolean.TRUE.equals(restTemplate
            .postForObject("http://data/user/login", user, Boolean.class));

        if (!successfulLogin) {
            throw new IllegalArgumentException("Username or password invalid for user %s!"
                .formatted(user.getUsername()));
        }

        return jwtProvider.generateToken(user.getUsername(), 5L, Set.of(ROLE_USER));
    }
}
