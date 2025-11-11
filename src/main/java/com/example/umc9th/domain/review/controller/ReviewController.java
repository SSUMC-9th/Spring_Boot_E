package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponseDTO; // 👈 DTO 임포트
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.api.ApiResponse; // 👈 ApiResponse 임포트
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    // 6주차 미션 API
    // 반환 타입을 Page<Review> -> ApiResponse<Page<ReviewResponseDTO>>로 변경
    @GetMapping("/my-reviews")
    public ApiResponse<Page<ReviewResponseDTO>> getMyReviews(
            @RequestParam(name = "memberId", defaultValue = "1") Long memberId,
            @RequestParam(name = "storeName", required = false) String storeName,
            @RequestParam(name = "starRating", required = false) Integer starRating,
            @PageableDefault(size = 10, sort = "createdAt,desc") Pageable pageable
    ) {
        Page<ReviewResponseDTO> reviewPage = reviewQueryService.getMyReviewList(memberId, storeName, starRating, pageable);

        // "성공 메서드"를 사용하여 최종 응답 포맷으로 감싸서 반환
        return ApiResponse.onSuccess(reviewPage);
    }
}