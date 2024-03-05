package com.gogoasa.c.data.controller;

import com.gogoasa.c.data.model.User;
import com.gogoasa.c.data.repository.UserRepository;
import com.gogoasa.c.data.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private UserRepository userRepository;
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return userRepository.findById(username)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody User user) {
       return ResponseEntity.ok(userService.login(user));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userRepository.deleteById(username);
        return ResponseEntity.noContent().build();
    }


}
