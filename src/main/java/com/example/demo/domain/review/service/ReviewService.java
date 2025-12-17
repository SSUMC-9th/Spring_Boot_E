package com.example.demo.domain.review.service;

import com.example.demo.domain.review.dto.ReviewResponseDto;
import com.example.demo.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponseDto> getMyReviews(Long userId, int page) {
        List<ReviewResponseDto> reviews = reviewRepository.findReviewsWithStoreNameAndRating(userId, page);

        // Stream + Builder 패턴 활용
        return reviews.stream()
                .map(r -> ReviewResponseDto.builder()
                        .reviewId(r.getReviewId())
                        .storeName(r.getStoreName())
                        .star(r.getStar())
                        .content(r.getContent())
                        .build())
                .toList();
    }
}
