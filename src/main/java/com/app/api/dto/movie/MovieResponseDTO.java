package com.app.api.dto.movie;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDTO {

    private Long id;
    
    private String title;

    private String genre;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm a", timezone = "Europe/Bucharest")
    private LocalDateTime createdAt;
}
