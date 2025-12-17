package com.example.demo.domain.review.converter;

import com.example.demo.domain.review.dto.ReviewResponseDto;
import com.example.demo.domain.review.entity.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDto toDto(Review review) {
        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getStoreName())
                .star(review.getStar())
                .content(review.getReviewContent())
                .build();
    }

    public static List<ReviewResponseDto> toDtoList(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewConverter::toDto)
                .collect(Collectors.toList());
    }
}
