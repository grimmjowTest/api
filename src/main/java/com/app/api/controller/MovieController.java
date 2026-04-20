package com.app.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.api.dto.movie.MovieRequestDTO;
import com.app.api.dto.movie.MovieResponseDTO;
import com.app.api.entity.Movie;
import com.app.api.mapper.MovieMapper;
import com.app.api.service.MovieService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        List<MovieResponseDTO> response = new ArrayList<>();

        for (Movie movie : movies) 
            response.add(MovieMapper.toDTO(movie));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById (@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);

        return ResponseEntity.ok(MovieMapper.toDTO(movie));
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie (@Valid @RequestBody MovieRequestDTO movieRequestDTO) {
        Movie movie = movieService.createMovie(movieRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(MovieMapper.toDTO(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovie (@PathVariable Long id, @Valid @RequestBody MovieRequestDTO movieRequestDTO) {
        Movie updatedMovie = movieService.updateMovie(id, movieRequestDTO);

        return ResponseEntity.ok(MovieMapper.toDTO(updatedMovie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie (@PathVariable Long id) {
        movieService.deleteMovie(id);

        return ResponseEntity.noContent().build();
    }
}