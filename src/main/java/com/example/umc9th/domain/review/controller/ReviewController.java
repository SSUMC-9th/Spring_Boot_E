// ReviewController.java
package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity; // ResponseEntity를 사용하면 더 좋습니다.
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews") // ⭐ 4. 공통 경로 추가
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService; // 쿼리 서비스 주입
    private final ReviewCommandService reviewCommandService; // Command Service (새로 추가)

    @GetMapping("/my")
    public ApiResponse<ReviewResDTO.ReviewListDTO> getMyReviews( // ⭐ 5. 반환 타입을 ApiResponse와 DTO로 변경
            // TODO: memberId는 나중에 Security에서 @AuthenticationPrincipal로 받아와야 합니다.
            @RequestParam(name = "memberId") Long memberId,

            // storeId 파라미터는 필수가 아님
            @RequestParam(name = "storeId", required = false) Long storeId,

            // stars 파라미터는 필수가 아님
            @RequestParam(name = "stars", required = false) Integer starRating,

            Pageable pageable
    ) {
        Page<Review> reviewPage = reviewService.getMyReviews(memberId, storeId, starRating, pageable);

        // ⭐ 6. Converter를 이용해 Entity(Page<Review>)를 DTO(ReviewListDTO)로 변환
        ReviewResDTO.ReviewListDTO result = ReviewConverter.toReviewListDTO(reviewPage);

        // ⭐ 7. ApiResponse에 담아 반환
        return ApiResponse.onSuccess(result);
    }
    // [8주차 필수 미션] 가게에 리뷰 추가하기 API
    @PostMapping("/stores/{storeId}")
    public ApiResponse<ReviewResDTO.ReviewPreviewDTO> addReview(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid ReviewReqDTO.WriteReviewDTO dto
    ) {
        // 🚨 하드코딩 요구사항: MemberId = 1L 사용
        Long memberId = 1L;

        // Service 호출 및 저장
        Review review = reviewCommandService.writeReview(memberId, storeId, dto);

        // 응답 DTO 변환 및 반환
        return ApiResponse.of(GeneralSuccessCode.CREATED, ReviewConverter.toReviewPreviewDTO(review));
    }
}