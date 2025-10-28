package com.example.demo.domain.review.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.repository.ReviewRepository;
import com.example.demo.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review writeReview(Member member, Store store, String reviewContent){
        Review review = Review.builder()
                .member(member)
                .store(store)
                .reviewContent(reviewContent)
                .build();

        return reviewRepository.save(review);
        //실제 INSERT 쿼리 실행
    }
}