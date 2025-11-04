// ReviewRepositoryCustom.java
package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    // "내가 작성한 리뷰" 목록을 동적 쿼리로 조회하는 메서드
    // storeId, starRating이 null일 수도 있음
    Page<Review> findMyReviews(Long memberId, Long storeId, Integer starRating, Pageable pageable);
}