package com.umc_study.mission_server.review.controller;

import com.umc_study.mission_server.common.Range;
import com.umc_study.mission_server.review.entity.Review;
import com.umc_study.mission_server.review.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> search(
        @RequestParam(required = false) Long memberId,
        @RequestParam(required = false) String storeName,
        @RequestParam(required = false) Integer score
    ) {
        Range scoreRange = null;
        if (score != null) {
            scoreRange = new Range(score, score + 1);
        }
        
        List<Review> reviews = reviewService.search(memberId, storeName, scoreRange);
        return ResponseEntity.ok(reviews);
    }
}
