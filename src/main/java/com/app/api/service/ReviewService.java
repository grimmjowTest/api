package com.app.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.api.entity.Movie;
import com.app.api.entity.Review;
import com.app.api.entity.User;
import com.app.api.repository.MovieRepository;
import com.app.api.repository.ReviewRepository;
import com.app.api.repository.UserRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public ReviewService (ReviewRepository reviewRepository, UserRepository userRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    public List<Review> getAllReviews () {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByUserId (Long userId) {
        userRepository.findById(userId)
                      .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return reviewRepository.findByUserId(userId);
    }

    public List<Review> getReviewsByMovieId (Long movieId) {
        movieRepository.findById(movieId)
                       .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        return reviewRepository.findByMovieId(movieId);
    }

    public Review saveReview (Review review) {
        User user = userRepository.findById(review.getUser().getId())
                                  .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Movie movie = movieRepository.findById(review.getMovie().getId())
                                     .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        review.setUser(user);
        review.setMovie(movie);

        return reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview (Long id) {
        Review review = reviewRepository.findById(id)
                                        .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        reviewRepository.delete(review);
    }

}
