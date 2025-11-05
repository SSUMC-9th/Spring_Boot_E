// ReviewService.java (인터페이스)
package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Page<Review> getMyReviews(Long memberId, Long storeId, Integer starRating, Pageable pageable);
}