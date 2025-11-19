package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.region.entity.QRegion;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.QReview; // Q클래스 임포트
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import java.util.List;

// JpaRepository를 상속받지 않고, 명명규칙을 지켜서 Spring이 구현체를 찾게 합니다.
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    // Q클래스 선언
    private static final QReview review = QReview.review;
    private static final QStore store = QStore.store; // QStore 선언
    private static final QRegion region = QRegion.region; // QRegion 선언

    @Override
    public List<Review> searchReview(Predicate predicate) {

        return queryFactory
                .selectFrom(review)
                .leftJoin(store).on(store.id.eq(review.store.id))
                .leftJoin(region).on(region.id.eq(store.region.id))
                .where(predicate) // Service에서 받은 WHERE 절 적용
                .fetch();
    }
}