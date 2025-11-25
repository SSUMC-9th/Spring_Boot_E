package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewRequest;
import com.example.demo.domain.review.dto.ReviewResponse;
import com.example.demo.domain.review.dto.ReviewResponseDto;
import com.example.demo.domain.review.dto.ReviewSearchRequest;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.service.AddReviewService;
import com.example.demo.domain.review.service.ReviewQueryService;
import com.example.demo.domain.review.service.ReviewService;
import com.example.demo.global.apiPayload.exception.ValidPage;
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
        private final ReviewService reviewService;

        @GetMapping("/search")
        public List<Review> searchReview(ReviewSearchRequest request) {
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

        @GetMapping
//        @Operation(summary = "내 리뷰 목록 조회", description = "사용자가 작성한 리뷰를 페이징 처리하여 조회합니다.")
        public List<ReviewResponseDto> getMyReviews(@ValidPage Integer page) {
            Long userId = 1L; // 실제로는 인증된 사용자 ID 가져오기
            return reviewService.getMyReviews(userId, page);
        }
    }