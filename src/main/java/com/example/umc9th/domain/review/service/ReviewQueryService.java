package com.example.umc9th.domain.review.service;

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

    public Page<Review> getMyReviewList(Long memberId, String storeName, Integer starRating, Pageable pageable) {

        // 리포지토리의 커스텀 메서드 호출
        return reviewRepository.findMyReviews(memberId, storeName, starRating, pageable);
    }
}