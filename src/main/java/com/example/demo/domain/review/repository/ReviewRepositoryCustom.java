package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.dto.ReviewResponseDto;

import java.util.List;

//보조 인터페이스 (Custom Repository)
public interface ReviewRepositoryCustom {
    List<ReviewResponseDto> findReviewsWithStoreNameAndRating(Long userId, int page);
}
