package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
}