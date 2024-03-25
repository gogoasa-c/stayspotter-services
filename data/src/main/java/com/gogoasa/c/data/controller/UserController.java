package com.gogoasa.c.data.controller;

import com.gogoasa.c.data.model.User;
import com.gogoasa.c.data.model.dto.UserCreationDto;
import com.gogoasa.c.data.model.dto.UserRequestDto;
import com.gogoasa.c.data.model.dto.UserResponseDto;
import com.gogoasa.c.data.repository.UserRepository;
import com.gogoasa.c.data.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private UserService userService;


    @PostMapping
    public ResponseEntity<UserCreationDto> createUser(@RequestBody UserCreationDto user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserRequestDto user) {
       return ResponseEntity.ok(userService.login(user));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }


}
