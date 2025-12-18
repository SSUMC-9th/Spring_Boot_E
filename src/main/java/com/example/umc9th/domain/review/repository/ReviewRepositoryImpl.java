package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

// Q-파일 static import (User로 변경됨에 주의!)
import static com.example.umc9th.domain.review.entity.QReview.review;
import static com.example.umc9th.domain.store.entity.QStore.store;
import static com.example.umc9th.domain.user.entity.QUser.user; // 👈 QMember -> QUser 변경

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> findMyReviews(Long userId, String storeName, Integer starRating, Pageable pageable) {

        // 1. 데이터 조회 쿼리
        List<Review> reviews = queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin() // 가게 조인
                .join(review.user, user).fetchJoin()   // 유저 조인 (QUser 사용)
                .where(
                        review.user.id.eq(userId), // 유저 아이디 조건
                        storeNameEq(storeName),    // 가게 이름 동적 조건
                        starRatingEq(starRating)   // 별점 동적 조건
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(review.createdAt.desc()) // 최신순 정렬
                .fetch();

        // 2. 전체 개수 조회 쿼리 (페이징용)
        Long total = queryFactory
                .select(review.count())
                .from(review)
                .join(review.store, store)
                .where(
                        review.user.id.eq(userId),
                        storeNameEq(storeName),
                        starRatingEq(starRating)
                )
                .fetchOne();

        return new PageImpl<>(reviews, pageable, total);
    }

    // --- 동적 쿼리 조건 메서드 ---

    private BooleanExpression storeNameEq(String storeName) {
        return StringUtils.hasText(storeName) ? store.name.eq(storeName) : null;
    }

    private BooleanExpression starRatingEq(Integer starRating) {
        if (starRating == null) {
            return null;
        }
        if (starRating == 5) {
            return review.star.eq(5.0f);
        }
        // 4점대 (4.0 <= star < 5.0)
        return review.star.goe(starRating.floatValue())
                .and(review.star.lt(starRating.floatValue() + 1));
    }
}