package com.gogoasa.c.data.service;

import com.gogoasa.c.data.model.User;
import com.gogoasa.c.data.model.dto.UserCreationDto;
import com.gogoasa.c.data.model.dto.UserRequestDto;
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

    public UserCreationDto createUser(UserCreationDto user) {
        userRepository.findById(user.getUsername()).ifPresent(u -> {
            throw new IllegalArgumentException("User already exists!");
        });

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        User userToSave = new User();
        userToSave.setUsername(user.getUsername());
//        userToSave.setEmail(user.getEmail());
        userToSave.setPassword(user.getPassword());

        userRepository.save(userToSave);

        return user;
    }

    public boolean login(UserRequestDto user) {
        return userRepository.findById(user.getUsername())
                .map(u -> passwordEncoder.matches(user.getPassword(), u.getPassword()))
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public UserResponseDto getUser(String username) {
         Optional<User> user = userRepository.findById(username);

         if (user.isEmpty()) {
             throw new RuntimeException("User not found!");
         }

         return new UserResponseDto(user.get().getUsername() /*user.get().getEmail()*/);
    }

    public void deleteUser(String username) {
        if (userRepository.findById(username).isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        userRepository.deleteById(username);
    }
}
