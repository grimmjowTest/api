package com.app.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.api.dto.auth.AuthRequestDTO;
import com.app.api.dto.auth.AuthResponseDTO;
import com.app.api.service.AuthService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        String username = authRequestDTO.getUsername();
        String password = authRequestDTO.getPassword();

        String token = authService.login(username, password);

        Date expiresAt = authService.getTokenExpirationDate(token);

        return ResponseEntity.ok(new AuthResponseDTO(token, username, expiresAt));
    }
}
