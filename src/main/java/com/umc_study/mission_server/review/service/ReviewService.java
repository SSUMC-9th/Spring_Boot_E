package com.umc_study.mission_server.review.service;

import com.umc_study.mission_server.review.entity.Review;
import com.umc_study.mission_server.review.repository.ReviewRepository;
import com.umc_study.mission_server.review.repository.ReviewSearchQueries;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> search(ReviewSearchQueries queries) {
        return reviewRepository.search(queries);
    }
}
