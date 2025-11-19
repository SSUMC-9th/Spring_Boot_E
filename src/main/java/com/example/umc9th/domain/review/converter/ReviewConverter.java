package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // 1. Entity -> Preview DTO
    public static ReviewResDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return ReviewResDTO.ReviewPreviewDTO.builder()
                .reviewId(review.getId())
                // Member와 Store 엔티티에서 필요한 필드를 가져옵니다.
                .memberName(review.getMember().getName())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .storeName(review.getStore().getStoreName())
                .build();
    }

    // 2. Page -> List DTO (Controller가 호출하는 메서드)
    public static ReviewResDTO.ReviewListDTO toReviewListDTO(Page<Review> reviewPage) {

        List<ReviewResDTO.ReviewPreviewDTO> reviewPreviewList = reviewPage.stream()
                .map(ReviewConverter::toReviewPreviewDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewListDTO.builder()
                .reviewList(reviewPreviewList)
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .listSize(reviewPreviewList.size())
                .build();
    }

    // ⭐ 3. DTO -> Entity 변환 (새로 추가) ⭐
    public static Review toReview(ReviewReqDTO.WriteReviewDTO dto, Member member, Store store) {
        return Review.builder()
                .rating(dto.getRating())
                .comment(dto.getComment())
                .member(member) // Service에서 찾은 Member 엔티티 주입
                .store(store)   // Service에서 찾은 Store 엔티티 주입
                .build();
    }
}