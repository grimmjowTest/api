package com.app.api.dto.movie;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestDTO {

    @NotBlank(message = "Title is mandatory and cannot be empty")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @Size(max = 50, message = "Genre cannot exceed 50 characters")
    private String genre;

    private LocalDate releaseDate;
}