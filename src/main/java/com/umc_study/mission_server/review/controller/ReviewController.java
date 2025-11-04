package com.umc_study.mission_server.review.controller;

import com.umc_study.mission_server.common.Range;
import com.umc_study.mission_server.review.dto.ReviewSearchRequest;
import com.umc_study.mission_server.review.entity.Review;
import com.umc_study.mission_server.review.repository.ReviewSearchQueries;
import com.umc_study.mission_server.review.repository.ReviewSearchQueries.ReviewSearchOrderMode;
import com.umc_study.mission_server.review.service.ReviewService;

import java.util.Collections;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;

    @PostMapping("/search")
    public ResponseEntity<List<Review>> search(ReviewSearchRequest request) {
        Range scoreRange = null;
        if (request.getScore() != null) {
            scoreRange = new Range(
                request.getScore(),
                request.getScore() + 1);
        }

        ReviewSearchOrderMode orderMode = ReviewSearchOrderMode.LATEST;
        if (request.getOrderMode() != null) {
            if (request.getOrderMode().equals("name")) {
                orderMode = ReviewSearchOrderMode.NAME;
            }
        }

        ReviewSearchQueries queries = ReviewSearchQueries.builder()
            .memberId(request.getMemberId())
            .storeNames(emptyIfNull(request.getStoreNames()))
            .regionNames(emptyIfNull(request.getRegionNames()))
            .scoreRange(scoreRange)
            .orderMode(orderMode)
            .page(request.getPage())
            .size(request.getSize())
            .build();

        List<Review> reviews = reviewService.search(queries);
        return ResponseEntity.ok(reviews);
    }

    private <T> List<T> emptyIfNull(List<T> list) {
        return Objects.requireNonNullElse(list, Collections.emptyList());
    }
}
