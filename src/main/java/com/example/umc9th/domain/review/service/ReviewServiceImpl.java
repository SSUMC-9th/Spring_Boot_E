// ReviewServiceImpl.java (구현 클래스)
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
@Transactional(readOnly = true) // 조회 기능이므로 readOnly = true
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getMyReviews(Long memberId, Long storeId, Integer starRating, Pageable pageable) {

        // Repository 계층에 만들어둔 동적 쿼리 메서드를 그대로 호출합니다.
        return reviewRepository.findMyReviews(memberId, storeId, starRating, pageable);
    }
}