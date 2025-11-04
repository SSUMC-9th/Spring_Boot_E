package com.umc_study.mission_server.review.repository;

import static com.umc_study.mission_server.member.entity.QMember.member;
import static com.umc_study.mission_server.review.entity.QReview.review;
import static com.umc_study.mission_server.review.entity.QReviewImage.reviewImage;
import static com.umc_study.mission_server.store.entity.QStore.store;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc_study.mission_server.review.domain.Review;
import com.umc_study.mission_server.review.domain.ReviewSearchQueries;
import com.umc_study.mission_server.review.domain.ReviewSearchQueries.ReviewSearchOrderMode;
import jakarta.persistence.EntityManager;
import java.util.List;
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
}
