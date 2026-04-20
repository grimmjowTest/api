package com.app.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.api.dto.review.ReviewRequestDTO;
import com.app.api.dto.review.ReviewResponseDTO;
import com.app.api.entity.Review;
import com.app.api.mapper.ReviewMapper;
import com.app.api.service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews() {

        List<Review> reviews = reviewService.getAllReviews();
        List<ReviewResponseDTO> response = new ArrayList<>();

        for (Review review : reviews)
            response.add(ReviewMapper.toDTO(review));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> getReviewById (@PathVariable Long id) {

        Review review = reviewService.getReviewById(id);

        return ResponseEntity.ok(ReviewMapper.toDTO(review));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByUserId (@PathVariable Long userId) {

        List<Review> reviews = reviewService.getReviewsByUserId(userId);
        List<ReviewResponseDTO> response = new ArrayList<>();

        for (Review review : reviews)
            response.add(ReviewMapper.toDTO(review));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByMovieId (@PathVariable Long movieId) {

        List<Review> reviews = reviewService.getReviewsByMovieId(movieId);
        List<ReviewResponseDTO> response = new ArrayList<>();

        for (Review review : reviews) {
            response.add(ReviewMapper.toDTO(review));
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview (@Valid @RequestBody ReviewRequestDTO dto) {
        Review review = reviewService.createReview(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ReviewMapper.toDTO(review));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewRequestDTO dto) {
        Review updatedReview = reviewService.updateReview(id, dto);

        return ResponseEntity.ok(ReviewMapper.toDTO(updatedReview));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);

        return ResponseEntity.noContent().build();
    }
}