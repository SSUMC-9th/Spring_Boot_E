package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewRequest;
import com.example.demo.domain.review.dto.ReviewResponse;
import com.example.demo.domain.review.dto.ReviewSearchRequest;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.service.AddReviewService;
import com.example.demo.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/reviews")
    public class ReviewController {

        private final ReviewQueryService reviewQueryService;
        private final AddReviewService addReviewService;

        @GetMapping("/search")
        public List<Review> searchReview(ReviewSearchRequest request){
            return reviewQueryService.searchReview(request);
        }

        @PostMapping("/{storeId}/reviews")
        public ResponseEntity<ReviewResponse> addReview(
                @PathVariable Long storeId,
                @RequestBody ReviewRequest request) {

            Long reviewId = addReviewService.addReview(storeId, request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ReviewResponse(reviewId, "리뷰 작성 성공"));
        }
    }