package com.app.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.api.dto.user.UserRequestDTO;
import com.app.api.dto.user.UserResponseDTO;
import com.app.api.entity.User;
import com.app.api.mapper.UserMapper;
import com.app.api.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        List<User> users = userService.getAllUsers();
        List<UserResponseDTO> response = new ArrayList<>();

        for (User user : users) 
            response.add(UserMapper.toDTO(user));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        User user = userService.createUser(userRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(UserMapper.toDTO(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        User updatedUser = userService.updateUser(id, userRequestDTO);

        return ResponseEntity.ok(UserMapper.toDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/movies/{movieId}")
    public ResponseEntity<Void> addMovieToUser(@PathVariable Long userId, @PathVariable Long movieId) {
        userService.addMovieToUser(userId, movieId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}