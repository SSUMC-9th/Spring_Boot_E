package com.umc_study.mission_server.review.dto;

import com.umc_study.mission_server.review.domain.Review;
import com.umc_study.mission_server.review.domain.ReviewImage;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record ReviewResponse(
    long id,
    double score,
    String content,
    String reply,
    LocalDateTime replyAt,
    long storeId,
    long authorId,
    List<String> imageUrls,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
            .id(review.getId())
            .score(review.getScore())
            .content(review.getContent())
            .reply(review.getReply())
            .replyAt(review.getReplyAt())
            .storeId(review.getStore().getId())
            .authorId(review.getAuthor().getId())
            .imageUrls(review.getImages().stream()
                .map(ReviewImage::getImageUrl)
                .toList())
            .createdAt(review.getCreatedAt())
            .updatedAt(review.getUpdatedAt())
            .build();
    }
}
