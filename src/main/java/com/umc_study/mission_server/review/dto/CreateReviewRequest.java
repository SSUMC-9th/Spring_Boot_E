package com.umc_study.mission_server.review.dto;

public record CreateReviewRequest(
    double score,
    String content
) {
}
