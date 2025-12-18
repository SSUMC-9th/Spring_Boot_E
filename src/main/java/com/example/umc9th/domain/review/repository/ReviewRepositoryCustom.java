package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    // "내가 쓴" 리뷰를 "가게 이름"과 "별점"으로 필터링 (동적 쿼리)
    // memberId -> userId로 변경
    Page<Review> findMyReviews(Long userId, String storeName, Integer starRating, Pageable pageable);
}