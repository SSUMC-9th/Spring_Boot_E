package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.QReview;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    // ReviewRepository 제거!
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> searchReview(Predicate predicate){
        QReview review = QReview.review;
        QStore store = QStore.store;

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.store, store)
                .where(predicate)
                .fetch();
    }
}