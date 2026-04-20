package com.app.api.mapper;

import com.app.api.dto.movie.MovieRequestDTO;
import com.app.api.dto.movie.MovieResponseDTO;
import com.app.api.entity.Movie;

public class MovieMapper {
    
    public static Movie toEntity (MovieRequestDTO movieRequestDTO) {
        if (movieRequestDTO == null)
            return null;

        Movie movie = new Movie();

        movie.setTitle(movieRequestDTO.getTitle());
        movie.setGenre(movieRequestDTO.getGenre());
        movie.setReleaseDate(movieRequestDTO.getReleaseDate());

        return movie;
    }

    public static MovieResponseDTO toDTO (Movie movie) {
        if (movie == null)
            return null;

        return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getReleaseDate(),
                movie.getCreatedAt()
        );
    }

    public static void updateEntity (Movie movie, MovieRequestDTO movieRequestDTO) {
        movie.setTitle(movieRequestDTO.getTitle());
        movie.setGenre(movieRequestDTO.getGenre());
        movie.setReleaseDate(movieRequestDTO.getReleaseDate());
    }
}
