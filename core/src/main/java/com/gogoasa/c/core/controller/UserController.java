package com.gogoasa.c.core.controller;

import com.gogoasa.c.core.model.User;
import com.gogoasa.c.core.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private JwtProvider jwtProvider;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody User user) {
        String jwt = jwtProvider.generateToken(user.getUsername(), 5L, Set.of("ROLE_ADMIN"));

        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/something")
    public ResponseEntity<String> doSomething() {
        return ResponseEntity.ok("SOMEHTING!!");
    }


}
