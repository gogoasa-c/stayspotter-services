package com.gogoasa.c.core.controller;

import com.gogoasa.c.core.model.User;
import com.gogoasa.c.core.model.dto.UserRequestDto;
import com.gogoasa.c.core.security.JwtProvider;
import com.gogoasa.c.core.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.gogoasa.c.core.utils.Constant.ROLE_USER;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody UserRequestDto user) {
        log.info("User {} logging in...", user.getUsername());
        return ResponseEntity.ok(userService.login(user));
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("User {} creating...", user.getUsername());
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/something")
    public ResponseEntity<String> getSomething() {
        return ResponseEntity.ok("Something");
    }
}
