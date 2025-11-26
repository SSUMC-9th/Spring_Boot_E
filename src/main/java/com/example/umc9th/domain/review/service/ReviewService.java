// ReviewService.java (인터페이스)
package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page);
    Page<Review> getMyReviews(Long memberId, Long storeId, Integer starRating, Pageable pageable);
    Page<Review> getStoreReviews(Long storeId, Integer starRating, Pageable pageable);
    Page<Review> getMyReviews(Long memberId, Pageable pageable);
}