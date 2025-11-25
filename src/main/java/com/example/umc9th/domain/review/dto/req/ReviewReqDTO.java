package com.example.umc9th.domain.review.dto.req;

import jakarta.validation.constraints.*;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class WriteReviewDTO {
        // Validation (6주차 미션 연계)
        @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
        @Max(value = 5, message = "별점은 5점 이하이어야 합니다.")
        @NotNull
        private Integer rating;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(max = 200)
        private String comment;
    }
}