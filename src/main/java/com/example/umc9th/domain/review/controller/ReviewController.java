// ReviewController.java
package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity; // ResponseEntity를 사용하면 더 좋습니다.
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews") // ⭐ 4. 공통 경로 추가
public abstract class ReviewController implements ReviewControllerDocs {

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
    // 가게의 리뷰 목록 조회
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 마크 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })

    @GetMapping("/reviews/search")
    public List<Review> searchReviews(
            @RequestParam String query,
            @RequestParam String type
    ) throws Exception {
        // 서비스에게 요청
        List<Review> result = reviewQueryService.searchReview(query, type);
        return result;
    }

    @Override
    @GetMapping("/stores/{storeId}")
    public ApiResponse<ReviewResDTO.ReviewListDTO> getStoreReviews(
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "starRating", required = false) Integer starRating,
            Pageable pageable
    ) {
        // Service 호출 (현재 3단계 구현 중)
        Page<Review> reviewPage = reviewService.getStoreReviews(storeId, starRating, pageable);

        // DTO 변환
        ReviewResDTO.ReviewListDTO result = ReviewConverter.toReviewListDTO(reviewPage);

        return ApiResponse.of(ReviewSuccessCode.FOUND, result);
    }
    @Override
    @GetMapping("/my")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestParam(name = "memberId") Long memberId,
            Pageable pageable
    ) {
        // 1. Service 호출
        Page<Review> reviewPage = reviewService.getMyReviews(memberId, pageable);

        // 2. Entity(Page<Review>)를 DTO(MyReviewListDTO)로 변환
        ReviewResDTO.MyReviewListDTO result = ReviewConverter.toMyReviewListDTO(reviewPage);

        // 3. ApiResponse에 담아 반환
        return ApiResponse.onSuccess(result);
    }
}