package com.gogoasa.c.data.service;

import com.gogoasa.c.data.model.User;
import com.gogoasa.c.data.model.dto.UserResponseDto;
import com.gogoasa.c.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        userRepository.findById(user.getUsername()).ifPresent(u -> {
            throw new IllegalArgumentException("User already exists");
        });

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public boolean login(User user) {
        return userRepository.findById(user.getUsername())
                .map(u -> passwordEncoder.matches(user.getPassword(), u.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public UserResponseDto getUser(String username) {
         Optional<User> user = userRepository.findById(username);

         if (user.isEmpty()) {
             throw new IllegalArgumentException("User not found");
         }

         return new UserResponseDto(user.get().getUsername(), user.get().getEmail());
    }
}
