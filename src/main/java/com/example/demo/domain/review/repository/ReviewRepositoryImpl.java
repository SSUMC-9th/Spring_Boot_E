package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.dto.ReviewResponseDto;
import com.example.demo.domain.review.entity.QReview;
import com.example.demo.domain.store.entity.QStore;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewResponseDto> findReviewsWithStoreNameAndRating(Long userId, int page) {
        QReview review = QReview.review;
        QStore store = QStore.store;

        return queryFactory
                .select(Projections.constructor(ReviewResponseDto.class,
                        review.id,
                        store.storeName,
                        review.star,
                        review.reviewContent,
                        review.createdAt.stringValue()))
                .from(review)
                .join(store).on(review.id.eq(store.id))
                .where(review.id.eq(userId))
                .offset((page - 1) * 10)
                .limit(10)
                .fetch();
    }
}
