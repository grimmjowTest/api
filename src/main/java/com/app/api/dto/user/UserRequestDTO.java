package com.app.api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank(message = "Username is mandatory and cannot be empty")
    @Size(max = 50, message = "Username cannot exceed 50 characters")
    private String username;

    @NotBlank(message = "Email is mandatory and cannot be empty")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is mandatory and cannot be empty")
    @Size(max = 255, message = "Password cannot exceed 255 characters")
    private String password;

}
