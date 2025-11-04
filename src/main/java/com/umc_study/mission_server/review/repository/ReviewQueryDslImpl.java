package com.umc_study.mission_server.review.repository;

import static com.umc_study.mission_server.member.entity.QMember.member;
import static com.umc_study.mission_server.review.entity.QReview.review;
import static com.umc_study.mission_server.review.entity.QReviewImage.reviewImage;
import static com.umc_study.mission_server.store.entity.QStore.store;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc_study.mission_server.review.entity.Review;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final EntityManager em;

    public List<Review> search(Predicate predicate) {
        JPQLQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
            .selectFrom(review)
            .join(member).on(member.id.eq(review.author.id)).fetchJoin()
            .join(store).on(store.id.eq(review.store.id)).fetchJoin()
            .leftJoin(reviewImage).on(reviewImage.review.id.eq(review.id)).fetchJoin()
            .where(predicate)
            .distinct()
            .fetch();
    }
}
