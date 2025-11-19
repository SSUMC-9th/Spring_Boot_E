package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    // 검색 API: Predicate를 받아 리뷰 리스트를 반환
    List<Review> searchReview(Predicate predicate);
}