package com.umc_study.mission_server.review.service;

import com.querydsl.core.BooleanBuilder;
import com.umc_study.mission_server.member.entity.QMember;
import com.umc_study.mission_server.review.entity.QReview;
import com.umc_study.mission_server.review.entity.Review;
import com.umc_study.mission_server.review.repository.ReviewQueryDsl;
import com.umc_study.mission_server.review.repository.ReviewRepository;
import com.umc_study.mission_server.store.entity.QStore;
import com.umc_study.mission_server.common.Range;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> search(
        Long memberId,
        String storeName,
        Range scoreRange
    ) {
        BooleanBuilder builder = new BooleanBuilder();

        if (memberId != null) {
            builder.and(QMember.member.id.eq(memberId));
        }

        if (storeName != null) {
            builder.and(QStore.store.name.eq(storeName));
        }

        if (scoreRange != null) {
            builder.and(QReview.review.score.goe(scoreRange.startInclusive()));
            builder.and(QReview.review.score.lt(scoreRange.endExclusive()));
        }

        return reviewRepository.search(builder);
    }
}
