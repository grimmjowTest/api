package com.app.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.api.dto.review.ReviewRequestDTO;
import com.app.api.entity.Movie;
import com.app.api.entity.Review;
import com.app.api.entity.User;
import com.app.api.mapper.ReviewMapper;
import com.app.api.repository.MovieRepository;
import com.app.api.repository.ReviewRepository;
import com.app.api.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;
    
    private final MovieRepository movieRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                               .orElseThrow(() -> new EntityNotFoundException("Review not found"));
    }

    public List<Review> getReviewsByUserId(Long userId) {
        userRepository.findById(userId)
                      .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return reviewRepository.findByUserId(userId);
    }

    public List<Review> getReviewsByMovieId(Long movieId) {
        movieRepository.findById(movieId)
                       .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        return reviewRepository.findByMovieId(movieId);
    }

    public Review createReview(ReviewRequestDTO reviewRequestDTO) {
        User user = userRepository.findById(reviewRequestDTO.getUserId())
                                  .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Movie movie = movieRepository.findById(reviewRequestDTO.getMovieId())
                                     .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        Review review = ReviewMapper.toEntity(reviewRequestDTO, user, movie);

        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, ReviewRequestDTO reviewRequestDTO) {
        Review existingReview = reviewRepository.findById(id)
                                                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        ReviewMapper.updateEntity(existingReview, reviewRequestDTO);

        return reviewRepository.save(existingReview);
    }

    @Transactional
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                                        .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        reviewRepository.delete(review);
    }
}
