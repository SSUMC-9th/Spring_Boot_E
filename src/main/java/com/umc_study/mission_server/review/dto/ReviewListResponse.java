package com.umc_study.mission_server.review.dto;

import com.umc_study.mission_server.review.domain.Review;
import java.util.List;
import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder
public record ReviewListResponse(
    List<ReviewResponse> reviewList,
    int listSize,
    int totalPage,
    long totalElements,
    boolean isFirst,
    boolean isLast
) {
    public static ReviewListResponse from(Page<Review> page) {
        return ReviewListResponse.builder()
            .reviewList(page.stream()
                .map(ReviewResponse::from)
                .toList())
            .listSize(page.getSize())
            .totalPage(page.getTotalPages())
            .totalElements(page.getTotalElements())
            .isFirst(page.isFirst())
            .isLast(page.isLast())
            .build();
    }
}
