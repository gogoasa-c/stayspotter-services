package com.gogoasa.c.core.service;

import com.gogoasa.c.core.model.User;
import com.gogoasa.c.core.model.dto.UserRequestDto;
import com.gogoasa.c.core.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static com.gogoasa.c.core.utils.Constant.ROLE_USER;

@RequiredArgsConstructor
@Service
public class UserService {

    private final JwtProvider jwtProvider;
    private final RestTemplate restTemplate;

    @Value("${data-service.url:http://localhost:8087}")
    private String dataServiceUrl;

    public User createUser(User user) {
        return restTemplate
            .postForObject("%s/user".formatted(dataServiceUrl), user, User.class);
    }

    public String login(UserRequestDto user) {
        boolean successfulLogin = Boolean.TRUE.equals(restTemplate
            .postForObject("%s/user/login".formatted(dataServiceUrl), user, Boolean.class));

        if (!successfulLogin) {
            throw new IllegalArgumentException("Username or password invalid for user %s!"
                .formatted(user.getUsername()));
        }

        return jwtProvider.generateToken(user.getUsername(), 20L, Set.of(ROLE_USER));
    }
}
