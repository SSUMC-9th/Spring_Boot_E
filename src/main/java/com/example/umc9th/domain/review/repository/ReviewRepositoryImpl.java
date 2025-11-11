package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
// Q-파일들을 static import 합니다. (경로는 본인 Q-파일 위치에 맞게!)
import static com.example.umc9th.domain.member.entity.QMember.member;
import static com.example.umc9th.domain.review.entity.QReview.review;
import static com.example.umc9th.domain.store.entity.QStore.store;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils; // 스프링의 StringUtils 사용

import java.util.List;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    // 1단계에서 Bean으로 등록한 JPAQueryFactory를 주입받습니다.
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> findMyReviews(Long memberId, String storeName, Integer starRating, Pageable pageable) {

        // 1. 데이터 조회 쿼리 (페이징 적용)
        List<Review> reviews = queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin() // 가게 이름으로 검색하기 위해 조인
                .join(review.member, member).fetchJoin() // N+1 문제 방지를 위해 fetchJoin
                .where(
                        // 1. "내가 작성한" 리뷰 (필수 조건)
                        review.member.id.eq(memberId),

                        // 2. 가게 이름 필터 (동적 쿼리)
                        storeNameEq(storeName),

                        // 3. 별점 필터 (동적 쿼리)
                        starRatingEq(starRating)
                )
                .offset(pageable.getOffset()) // 페이징
                .limit(pageable.getPageSize()) // 페이징
                .orderBy(review.createdAt.desc()) // 최신순 정렬
                .fetch();

        // 2. 전체 카운트 쿼리 (페이징을 위해 필요)
        Long total = queryFactory
                .select(review.count())
                .from(review)
                .join(review.store, store)
                .where(
                        review.member.id.eq(memberId),
                        storeNameEq(storeName),
                        starRatingEq(starRating)
                )
                .fetchOne();

        // Page 객체로 변환하여 반환
        return new PageImpl<>(reviews, pageable, total);
    }

    // ========== 동적 쿼리 메서드 (재사용 가능) ==========

    // 2. 가게 이름 필터 (storeName 파라미터가 null이 아니면 조건 추가)
    private BooleanExpression storeNameEq(String storeName) {
        // StringUtils.hasText()는 null이거나 빈 문자열("")일 때 false를 반환
        return StringUtils.hasText(storeName) ? store.name.eq(storeName) : null;
    }

    // 3. 별점 필터 (starRating 파라미터가 null이 아니면 조건 추가)
    private BooleanExpression starRatingEq(Integer starRating) {
        if (starRating == null) {
            return null; // null이면 조건을 적용하지 않음
        }

        // "5점"은 5.0
        if (starRating == 5) {
            return review.star.eq(5.0f);
        }

        // "4점대" (4.0 ~ 4.9), "3점대" (3.0 ~ 3.9) ...
        // (star >= X AND star < X+1)
        return review.star.goe(starRating.floatValue())
                .and(review.star.lt(starRating.floatValue() + 1));
    }
}