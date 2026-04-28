package com.app.api.dto.auth;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;

    private final String type = "Bearer";

    private String username;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm a", timezone = "Europe/Bucharest")
    private Date expiresAt;
}