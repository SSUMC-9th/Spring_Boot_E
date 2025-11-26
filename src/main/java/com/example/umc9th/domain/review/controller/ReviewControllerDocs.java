package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewControllerDocs {
    @Operation(
            summary = "가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰를 모두 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/stores/{storeId}")
    ApiResponse<ReviewResDTO.ReviewListDTO> getStoreReviews(
            @PathVariable(name = "storeId") Long storeId, // @PathVariable 추가
            @RequestParam(name = "starRating", required = false) Integer starRating, // @RequestParam 추가
            Pageable pageable // Pageable 추가
    );
    @Operation(
            summary = "내가 작성한 리뷰 목록 조회 API",
            description = "특정 회원이 작성한 리뷰 목록을 페이지네이션으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            // ... 다른 응답 코드 명세
    })
    @GetMapping("/my")
    ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            // @Parameter 등을 사용하여 memberId는 Security에서 가져옴을 명세
            @RequestParam(name = "memberId") Long memberId,
            Pageable pageable
    );
}
