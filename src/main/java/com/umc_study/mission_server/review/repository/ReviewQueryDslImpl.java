package com.umc_study.mission_server.review.repository;

import static com.umc_study.mission_server.member.entity.QMember.member;
import static com.umc_study.mission_server.review.domain.QReview.review;
import static com.umc_study.mission_server.review.domain.QReviewImage.reviewImage;
import static com.umc_study.mission_server.store.entity.QStore.store;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc_study.mission_server.review.domain.Review;
import com.umc_study.mission_server.review.domain.ReviewSearchQueries;
import com.umc_study.mission_server.review.domain.ReviewSearchQueries.ReviewSearchOrderMode;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

@Service
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final JPAQueryFactory queryFactory;

    public ReviewQueryDslImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Review> search(ReviewSearchQueries queries) {
        var query = queryFactory
            .selectFrom(review)
            .join(member).on(member.id.eq(review.author.id)).fetchJoin()
            .join(store).on(store.id.eq(review.store.id)).fetchJoin()
            .leftJoin(reviewImage).on(reviewImage.review.id.eq(review.id)).fetchJoin()
            .where(buildSearchPredicate(queries))
            .orderBy(buildOrderSpecifier(queries.getOrderMode()))
            .distinct();

        if (queries.getPage() != null && queries.getSize() != null) {
            query = query
                .offset((long) (queries.getPage() - 1) * queries.getSize())
                .limit(queries.getSize());
        }

        return query.fetch();
    }

    private Predicate buildSearchPredicate(ReviewSearchQueries queries) {
        BooleanBuilder builder = new BooleanBuilder();

        if (queries.getMemberId() != null) {
            builder.and(member.id.eq(queries.getMemberId()));
        }

        Predicate storeNamePredicate = buildStoreNamePredicate(queries.getStoreNames());
        if (storeNamePredicate != null) {
            builder.and(storeNamePredicate);
        }

        Predicate regionNamePredicate = buildRegionNamePredicate(queries.getRegionNames());
        if (regionNamePredicate != null) {
            builder.and(regionNamePredicate);
        }

        if (queries.getScoreRange() != null) {
            builder.and(review.score.goe(queries.getScoreRange().startInclusive()));
            builder.and(review.score.lt(queries.getScoreRange().endExclusive()));
        }

        return builder;
    }

    private Predicate buildStoreNamePredicate(List<String> storeNames) {
        if (storeNames == null || storeNames.isEmpty()) {
            return null;
        }

        BooleanBuilder builder = new BooleanBuilder();
        for (String storeName : storeNames) {
            builder.or(store.name.contains(storeName));
        }

        return builder;
    }

    private Predicate buildRegionNamePredicate(List<String> regionNames) {
        if (regionNames == null || regionNames.isEmpty()) {
            return null;
        }

        BooleanBuilder builder = new BooleanBuilder();
        for (String regionName : regionNames) {
            builder.or(store.address1.eq(regionName));
        }

        return builder;
    }

    private OrderSpecifier<?> buildOrderSpecifier(ReviewSearchOrderMode orderMode) {
        return switch (orderMode) {
            case LATEST -> review.createdAt.desc();
            case NAME -> store.name.asc();
        };
    }

    @Override
    public Page<Review> findAllByStoreId(Long storeId, Pageable pageable) {
        // 리뷰 + 이미지 가져오기
        List<Review> content = queryFactory
            .selectFrom(review)
            .where(review.store.id.eq(storeId))
            .distinct()
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        // 갯수 세는 쿼리
        JPAQuery<Long> countQuery = queryFactory
            .select(review.countDistinct())
            .from(review)
            .where(review.store.id.eq(storeId));

        // content 가 하나면 countQuery 생략하는 최적화 적용
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Review> findAllByMemberId(Long memberId, Pageable pageable) {
        // 리뷰 + 이미지 가져오기
        List<Review> content = queryFactory
            .selectFrom(review)
            .where(review.author.id.eq(memberId))
            .distinct()
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        // 갯수 세는 쿼리
        JPAQuery<Long> countQuery = queryFactory
            .select(review.countDistinct())
            .from(review)
            .where(review.author.id.eq(memberId));

        // content 가 하나면 countQuery 생략하는 최적화 적용
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
