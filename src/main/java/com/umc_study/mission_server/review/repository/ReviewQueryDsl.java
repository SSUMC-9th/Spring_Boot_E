package com.umc_study.mission_server.review.repository;

import com.umc_study.mission_server.review.domain.Review;
import com.umc_study.mission_server.review.domain.ReviewSearchQueries;
import java.util.List;

public interface ReviewQueryDsl {
    List<Review> search(ReviewSearchQueries queries);
}
