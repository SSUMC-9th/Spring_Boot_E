package com.umc_study.mission_server.review.controller;

import com.umc_study.mission_server.common.response.ApiResponse;
import com.umc_study.mission_server.review.dto.ReviewSearchRequest;
import com.umc_study.mission_server.review.domain.Review;
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

    @PostMapping("/search")
    public ApiResponse<List<Review>> search(@RequestBody ReviewSearchRequest request) {
        List<Review> reviews = reviewService.search(request);
        return ApiResponse.ok(reviews);
    }
}
