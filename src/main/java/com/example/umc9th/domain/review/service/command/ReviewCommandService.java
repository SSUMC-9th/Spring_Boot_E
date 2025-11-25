package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.entity.Review;

public interface ReviewCommandService {
    Review writeReview(Long memberId, Long storeId, ReviewReqDTO.WriteReviewDTO dto);
}