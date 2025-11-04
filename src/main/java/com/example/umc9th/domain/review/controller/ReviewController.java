package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
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


    @GetMapping("/my-reviews")
    public Page<Review> getMyReviews(
            // @AuthenticationPrincipal 같은 어노테이션으로 실제 사용자 ID를 받아와야 합니다.
            // 여기서는 임시로 1L을 사용합니다.
            @RequestParam(name = "memberId", defaultValue = "1") Long memberId,

            // storeName 파라미터는 선택적(required = false)
            @RequestParam(name = "storeName", required = false) String storeName,

            // starRating 파라미터는 선택적(required = false)
            @RequestParam(name = "starRating", required = false) Integer starRating,

            // ?page=0&size=10&sort=createdAt,desc (최신순 정렬)
            @PageableDefault(size = 10, sort = "createdAt,desc") Pageable pageable
    ) {
        Page<Review> reviewPage = reviewQueryService.getMyReviewList(memberId, storeName, starRating, pageable);

        // API 응답 형식에 맞게 DTO로 변환하여 반환 (지금은 엔티티 그대로 반환)
        // return ApiResponse.onSuccess(reviewPage.map(ReviewDto::new));
        return reviewPage;
    }
}