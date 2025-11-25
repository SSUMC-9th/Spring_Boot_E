package com.umc_study.mission_server.review.controller;

import com.umc_study.mission_server.common.response.ApiResponse;
import com.umc_study.mission_server.review.dto.CreateReviewRequest;
import com.umc_study.mission_server.review.dto.ReviewListResponse;
import com.umc_study.mission_server.review.dto.ReviewSearchRequest;
import com.umc_study.mission_server.review.domain.Review;
import com.umc_study.mission_server.review.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;

    @PostMapping("/reviews/search")
    @Operation(
        summary = "리뷰 검색"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    })
    public ApiResponse<List<Review>> search(@RequestBody ReviewSearchRequest request) {
        List<Review> reviews = reviewService.search(request);
        return ApiResponse.ok(reviews);
    }

    @GetMapping("/reviews/my")
    @Operation(
        summary = "내가 작성한 리뷰 목록",
        description = "내가 작성한 리뷰 목록을 가져옵니다. 페이지네이션 제공"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    })
    public ApiResponse<ReviewListResponse> getMyReviewList(
        @RequestParam Long memberId,
        @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 15);
        ReviewListResponse list = reviewService.getReviewListByMemberId(memberId, pageRequest);
        return ApiResponse.ok(list);
    }

    @GetMapping("/stores/{storeId}/reviews")
    @Operation(
        summary = "가게 리뷰 목록 조회",
        description = "특정 가게의 리뷰 조회를 모두 조회합니다. 페이지네이션 제공"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "찾을 수 없는 가게")
    })
    public ApiResponse<ReviewListResponse> getReviewList(
        @PathVariable Long storeId,
        @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 15);
        ReviewListResponse list = reviewService.getReviewListByStoreId(storeId, pageRequest);
        return ApiResponse.ok(list);
    }

    @PostMapping("/stores/{storeId}/reviews")
    @Operation(
        summary = "리뷰 작성"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    })
    public ApiResponse<Review> createReview(
        @PathVariable Long storeId,
        @RequestBody CreateReviewRequest request
    ) {
        Review review = reviewService.create(storeId, 1L, request);
        return ApiResponse.ok(review);
    }
}
