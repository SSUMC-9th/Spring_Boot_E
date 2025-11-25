package com.example.demo.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponseDto {
    private Long reviewId;
    private String storeName;
    private int star;
    private String content;
}
