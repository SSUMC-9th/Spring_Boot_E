package com.example.demo.domain.review.service;

import com.example.demo.domain.review.dto.ReviewSearchRequest;
import com.example.demo.domain.review.entity.QReview;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.repository.ReviewRepository;
import com.example.demo.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(ReviewSearchRequest request) {

        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();

        // 가게명으로 검색 (null이 아닐 때만)
        if(request.getStoreName() != null && !request.getStoreName().isEmpty()){
            builder.and(review.store.storeName.eq(request.getStoreName()));
        }

        // 별점으로 검색 (null이 아닐 때만)
        if(request.getStar() != null){
            builder.and(review.star.goe(request.getStar()));
        }

        return reviewRepository.searchReview(builder);
    }
}