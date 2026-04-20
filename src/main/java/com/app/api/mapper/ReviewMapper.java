package com.app.api.mapper;

import com.app.api.dto.review.ReviewRequestDTO;
import com.app.api.dto.review.ReviewResponseDTO;
import com.app.api.entity.Movie;
import com.app.api.entity.Review;
import com.app.api.entity.User;

public class ReviewMapper {

    public static Review toEntity (ReviewRequestDTO reviewRequestDTO, User user, Movie movie) {
        if (reviewRequestDTO == null)
            return null;
    
        Review review = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setRating(reviewRequestDTO.getRating());
        review.setComment(reviewRequestDTO.getComment());

        return review;
    }

    public static ReviewResponseDTO toDTO (Review review) {
        if (review == null)
            return null;

        return new ReviewResponseDTO(
                review.getId(),
                review.getUser().getId(),
                review.getUser().getUsername(),
                review.getMovie().getId(),
                review.getMovie().getTitle(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt()
        );
    }

    public static void updateEntity (Review review, ReviewRequestDTO reviewRequestDTO) {
        review.setRating(reviewRequestDTO.getRating());
        review.setComment(reviewRequestDTO.getComment());
    }

}