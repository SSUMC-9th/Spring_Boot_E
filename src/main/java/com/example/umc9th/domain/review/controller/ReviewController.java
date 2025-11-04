// ReviewController.java
package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity; // ResponseEntity를 사용하면 더 좋습니다.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews/my")
    public ResponseEntity<Page<Review>> getMyReviews(
            // TODO: memberId는 나중에 Security에서 @AuthenticationPrincipal로 받아와야 합니다.
            @RequestParam(name = "memberId") Long memberId,

            // storeId 파라미터는 필수가 아님
            @RequestParam(name = "storeId", required = false) Long storeId,

            // stars 파라미터는 필수가 아님
            @RequestParam(name = "stars", required = false) Integer starRating,

            Pageable pageable
    ) {
        Page<Review> reviewPage = reviewService.getMyReviews(memberId, storeId, starRating, pageable);

        // Page 객체를 ResponseEntity.ok()로 감싸서 반환하면
        // HTTP 200 OK 상태 코드와 함께 JSON 본문이 전송됩니다.
        return ResponseEntity.ok(reviewPage);
    }
}