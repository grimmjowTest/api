package com.app.api.dto.movie;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private LocalDate releaseDate;

    private LocalDateTime createdAt;
}
