package com.app.api.dto.review;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
    
    private Long id;

    private Long userId;
    private String username;

    private Long movieId;
    private String movieTitle;

    private int rating;

    private String comment;

    private LocalDateTime createdAt;
}