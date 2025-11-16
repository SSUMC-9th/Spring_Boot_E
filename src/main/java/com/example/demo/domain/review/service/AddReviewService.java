package com.example.demo.domain.review.service;

import com.example.demo.domain.review.dto.ReviewRequest;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.repository.ReviewRepository;
import com.example.demo.domain.store.entity.Store;
import com.example.demo.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public Long addReview(Long storeId, ReviewRequest reviewRequest) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        Review review = Review.builder()
                .store(store)
                .reviewContent(reviewRequest.getContent())
                .star(reviewRequest.getStar())
                .build();

        reviewRepository.save(review);
        return review.getId();


    }
}
