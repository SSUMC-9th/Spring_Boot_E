package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResponseDTO; // 👈 DTO 임포트
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    // 반환 타입을 Page<Review> -> Page<ReviewResponseDTO>로 변경
    public Page<ReviewResponseDTO> getMyReviewList(Long memberId, String storeName, Integer starRating, Pageable pageable) {

        // 리포지토리의 커스텀 메서드 호출 (Entity 페이지로 받음)
        Page<Review> reviewPage = reviewRepository.findMyReviews(memberId, storeName, starRating, pageable);

        // Entity Page -> DTO Page로 변환하여 반환
        return reviewPage.map(ReviewResponseDTO::from);
    }
}