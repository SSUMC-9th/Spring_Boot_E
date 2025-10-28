package com.umc_study.mission_server.review.controller;

import com.umc_study.mission_server.review.entity.Review;
import com.umc_study.mission_server.review.repository.ReviewRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewRepository reviewRepository;

    @GetMapping
    public ResponseEntity<List<Review>> test() {
        // all
        List<Review> reviews = reviewRepository.findAll();

        // read
        Optional<Review> review = reviewRepository.findById(1L);

        // create
        Review newReview = Review.builder()
            .content("맛잇어요")
            .build();
        reviewRepository.save(newReview);

        // delete
        reviewRepository.delete(review.get());

        return ResponseEntity.ok(reviews);
    }
}
