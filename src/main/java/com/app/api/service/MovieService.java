package com.app.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.api.dto.movie.MovieRequestDTO;
import com.app.api.entity.Movie;
import com.app.api.mapper.MovieMapper;
import com.app.api.repository.MovieRepository;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                              .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    public Movie createMovie(MovieRequestDTO movieRequestDTO) {
        Movie movie = MovieMapper.toEntity(movieRequestDTO);

        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, MovieRequestDTO movieRequestDTO) {
        Movie existingMovie = movieRepository.findById(id)
                                             .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        MovieMapper.updateEntity(existingMovie, movieRequestDTO);

        return movieRepository.save(existingMovie);
    }

    @Transactional
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                                     .orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        movieRepository.delete(movie);
    }
}
