package com.app.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.api.entity.User;
import com.app.api.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers () {
        return userRepository.findAll();
    }

    public User getUserById (Long id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User saveUser (User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new IllegalArgumentException("Username already exists");

        if (userRepository.existsByEmail(user.getEmail()))
            throw new IllegalArgumentException("Email already exists");

        return userRepository.save(user);
    }

    public User updateUser (Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                                          .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (userRepository.existsByUsernameAndIdNot(existingUser.getUsername(), id))
            throw new IllegalArgumentException("Username already exists");

        if (userRepository.existsByEmailAndIdNot(existingUser.getEmail(), id))
            throw new IllegalArgumentException("Email already exists");

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        return userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(user);
    }
    
}
