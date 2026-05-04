package com.app.api.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.app.api.entity.User.Role;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;

    private String username;

    private String email;

    private Role role;
}