package com.app.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.api.entity.Movie;
import com.app.api.repository.MovieRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById (Long id) {
        return movieRepository.findById(id)
                              .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    public Movie saveMovie (Movie movie) {
        return movieRepository.save(movie);
    }

    @Transactional
    public void deleteMovie (Long id) {
        Movie movie = movieRepository.findById(id)
                                     .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        movieRepository.delete(movie);
    }

}
