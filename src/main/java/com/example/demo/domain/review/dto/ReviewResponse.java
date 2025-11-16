package com.example.demo.domain.review.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReviewResponse {
    private Long reviewId;
    private String message;

    public ReviewResponse(Long reviewId, String message) {
        this.reviewId = reviewId;
        this.message = message;
    }
}
