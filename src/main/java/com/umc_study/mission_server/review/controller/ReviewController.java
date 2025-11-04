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
        List<Review> reviews = reviewService.search(request);
        return ResponseEntity.ok(reviews);
    }
}
