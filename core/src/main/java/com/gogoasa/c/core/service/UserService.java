package com.gogoasa.c.core.service;

import com.gogoasa.c.core.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class UserService {

    private RestTemplate restTemplate;

    public User createUser(User user) {
        return restTemplate
            .postForObject("http://localhost:5000/data/user/", user, User.class);
    }
}
