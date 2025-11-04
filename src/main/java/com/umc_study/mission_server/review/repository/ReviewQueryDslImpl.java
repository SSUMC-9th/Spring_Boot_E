package com.umc_study.mission_server.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc_study.mission_server.member.entity.QMember;
import com.umc_study.mission_server.review.entity.QReview;
import com.umc_study.mission_server.review.entity.Review;
import com.umc_study.mission_server.store.entity.QStore;
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
            .selectFrom(QReview.review)
            .join(QMember.member).on(QMember.member.id.eq(QReview.review.author.id))
            .join(QStore.store).on(QStore.store.id.eq(QReview.review.store.id))
            .where(predicate)
            .fetch();
    }
}
