package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Review;
import java.time.LocalDateTime;

// "record"를 사용하면 Getter, 생성자, equals, hashCode가 자동 생성됩니다.
public record ReviewResponseDTO(
        Long reviewId,
        String memberName,
        String storeName,
        Float star,
        String content,
        LocalDateTime createdAt
) {
    // Entity -> DTO 변환을 위한 정적 팩토리 메서드
    public static ReviewResponseDTO from(Review review) {
        return new ReviewResponseDTO(
                review.getId(),
                review.getMember().getName(), // fetchJoin으로 N+1 방지됨
                review.getStore().getName(),  // fetchJoin으로 N+1 방지됨
                review.getStar(),
                review.getContent(),
                review.getCreatedAt()
        );
    }
}