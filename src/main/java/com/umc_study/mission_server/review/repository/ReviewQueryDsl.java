package com.umc_study.mission_server.review.repository;

import com.querydsl.core.types.Predicate;
import com.umc_study.mission_server.review.entity.Review;
import java.util.List;

public interface ReviewQueryDsl {
    List<Review> search(ReviewSearchQueries queries);
}
