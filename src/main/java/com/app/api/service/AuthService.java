package com.app.api.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.app.api.entity.User;
import com.app.api.repository.UserRepository;
import com.app.api.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                                  .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        return jwtUtil.generateToken(username);
    }

    public Date getTokenExpirationDate(String token) {
        return jwtUtil.extractExpirationDate(token);
    }
}
