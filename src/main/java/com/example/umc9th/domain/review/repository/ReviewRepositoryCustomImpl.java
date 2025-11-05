// com/example/umc9th/domain/review/repository/ReviewRepositoryCustomImpl.java
package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
// Q파일을 임포트합니다. (build/generated... 경로에 생성된 파일)
import com.example.umc9th.domain.review.entity.QReview;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

// 이 클래스는 JpaRepository를 상속받지 않습니다!
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    // 1번에서 Bean으로 등록한 JPAQueryFactory를 주입받습니다.
    private final JPAQueryFactory queryFactory;

    // QReview 객체를 static final로 만들어두면 편리합니다.
    private static final QReview review = QReview.review;

    @Override
    public Page<Review> findMyReviews(Long memberId, Long storeId, Integer starRating, Pageable pageable) {

        // 1. 본 쿼리: 데이터를 리스트로 조회
        List<Review> content = queryFactory
                .selectFrom(review)
                .where(
                        // 1. memberId는 필수 조건
                        review.member.id.eq(memberId),

                        // 2. storeId는 null이 아니면 조건 추가 (동적 쿼리)
                        storeIdEq(storeId),

                        // 3. starRating은 null이 아니면 조건 추가 (동적 쿼리)
                        starRatingEq(starRating)
                )
                .offset(pageable.getOffset())   // 페이징
                .limit(pageable.getPageSize())  // 페이징
                .orderBy(review.createdAt.desc()) // 최신순 정렬
                .fetch(); // 리스트로 반환

        // 2. 카운트 쿼리: 총 개수 조회 (페이징을 위해 필요)
        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(
                        review.member.id.eq(memberId),
                        storeIdEq(storeId),
                        starRatingEq(starRating)
                )
                .fetchOne(); // 단 건(Long)으로 반환

        // 3. Page 객체로 만들어서 반환
        return new PageImpl<>(content, pageable, total);
    }

    // == 동적 쿼리 조건을 위한 헬퍼 메서드 == //

    // 가게(Store) ID 조건
    private BooleanExpression storeIdEq(Long storeId) {
        // storeId가 null이면, 조건을 추가하지 않음 (null 리턴)
        return storeId != null ? review.store.id.eq(storeId) : null;
    }

    // 별점(Star Rating) 조건
    private BooleanExpression starRatingEq(Integer starRating) {
        if (starRating == null) {
            return null; // starRating이 null이면 조건 무시
        }

        // "4점대" (4) -> 4.0 <= rating < 5.0
        // "5점" (5) -> 5.0 <= rating < 6.0
        return review.rating.goe(starRating)
                .and(review.rating.lt(starRating + 1));
    }
}