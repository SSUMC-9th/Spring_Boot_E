package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    // 개별 리뷰 정보
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreviewDTO {
        private Long reviewId;
        private String memberName;
        private Integer rating; // 별점
        private String comment;
        private LocalDateTime createdAt;
        private String storeName; // 가게 이름
    }

    // 페이징 정보 포함된 전체 응답
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewListDTO {
        List<ReviewPreviewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
    // 9주차
    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ){}
    // 1. 목록의 각 항목을 담는 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewPreviewDTO {
        private Long reviewId;
        private String memberName; // 닉네임 (Review.member.name)
        private Integer rating; // 리뷰 점수
        private LocalDateTime createdAt; // 작성 날짜
        private String comment; // 리뷰 내용 (화면에 표시되므로 포함)
        // 기타 필요한 정보 (가게 이름 등) 추가 가능
    }

    // 2. 페이징 정보를 포함한 전체 목록 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewListDTO {
        List<MyReviewPreviewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}