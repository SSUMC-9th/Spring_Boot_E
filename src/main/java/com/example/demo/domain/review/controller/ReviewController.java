package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewSearchRequest;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/reviews")
    public class ReviewController {

        private final ReviewQueryService reviewQueryService;

        @GetMapping("/search")
        public List<Review> searchReview(ReviewSearchRequest request){
            return reviewQueryService.searchReview(request);
        }
    }