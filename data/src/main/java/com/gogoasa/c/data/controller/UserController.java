package com.gogoasa.c.data.controller;

import com.gogoasa.c.data.model.User;
import com.gogoasa.c.data.model.dto.UserCreationDto;
import com.gogoasa.c.data.model.dto.UserRequestDto;
import com.gogoasa.c.data.model.dto.UserResponseDto;
import com.gogoasa.c.data.repository.UserRepository;
import com.gogoasa.c.data.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserCreationDto> createUser(@RequestBody UserCreationDto user) {
        log.info("Received request to create user...");
        log.debug("Request: {}", user);

        UserCreationDto newUser = userService.createUser(user);
        log.debug("Response: {}", newUser);

        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String username) {
        log.info("Received request to get user info...");
        log.debug("Request: username = {}", username);

        UserResponseDto user = userService.getUser(username);
        log.debug("Response: {}", user);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserRequestDto user) {
        log.info("Received request to login...");
        log.debug("Request: {}", user);

        Boolean successfulLogin = userService.login(user);
        log.debug("Response: {}", successfulLogin);

        return ResponseEntity.ok(successfulLogin);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        log.info("Received request to delete user...");
        log.debug("Request: username = {}", username);

        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }


}
