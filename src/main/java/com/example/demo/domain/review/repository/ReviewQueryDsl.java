package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {
    List<Review> searchReview(Predicate predicate);
}
