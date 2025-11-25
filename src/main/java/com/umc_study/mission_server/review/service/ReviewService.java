package com.umc_study.mission_server.review.service;

import com.umc_study.mission_server.common.Range;
import com.umc_study.mission_server.member.dto.MemberResponse;
import com.umc_study.mission_server.member.entity.Member;
import com.umc_study.mission_server.member.exception.MemberErrorCode;
import com.umc_study.mission_server.member.exception.MemberException;
import com.umc_study.mission_server.member.repository.MemberRepository;
import com.umc_study.mission_server.review.dto.CreateReviewRequest;
import com.umc_study.mission_server.review.dto.ReviewListResponse;
import com.umc_study.mission_server.review.dto.ReviewSearchRequest;
import com.umc_study.mission_server.review.domain.Review;
import com.umc_study.mission_server.review.exception.ReviewErrorCode;
import com.umc_study.mission_server.review.exception.ReviewException;
import com.umc_study.mission_server.review.repository.ReviewRepository;
import com.umc_study.mission_server.review.domain.ReviewSearchQueries;
import com.umc_study.mission_server.review.domain.ReviewSearchQueries.ReviewSearchOrderMode;
import com.umc_study.mission_server.store.entity.Store;
import com.umc_study.mission_server.store.exception.StoreErrorCode;
import com.umc_study.mission_server.store.exception.StoreException;
import com.umc_study.mission_server.store.repository.StoreRepository;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    public Review create(Long storeId, Long memberId, CreateReviewRequest request) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Review review = Review.builder()
            .score(request.score())
            .content(request.content())
            .reply(null)
            .replyAt(null)
            .store(store)
            .author(member)
            .build();
        reviewRepository.save(review);
        return review;
    }

    public ReviewListResponse getReviewListByStoreId(Long storeId, Pageable pageable) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Page<Review> page = reviewRepository.findAllByStoreId(storeId, pageable);
        return ReviewListResponse.from(page);
    }

    public List<Review> search(ReviewSearchRequest request) {
        ReviewSearchQueries queries = getQueryFromRequest(request);
        return reviewRepository.search(queries);
    }

    private ReviewSearchQueries getQueryFromRequest(ReviewSearchRequest request) {
        Range scoreRange = null;
        if (request.getScore() != null) {
            scoreRange = new Range(
                request.getScore(),
                request.getScore() + 1);
        }

        ReviewSearchOrderMode orderMode = ReviewSearchOrderMode.LATEST;
        if (request.getOrderMode() != null) {
            if (request.getOrderMode().equals("name")) {
                orderMode = ReviewSearchOrderMode.NAME;
            }
            else if (request.getOrderMode().equals("latest")) {
                orderMode = ReviewSearchOrderMode.LATEST;
            }
            else {
                throw new ReviewException(ReviewErrorCode.BAD_SEARCH_ORDER_MODE);
            }
        }

        return ReviewSearchQueries.builder()
            .memberId(request.getMemberId())
            .storeNames(parseStoreNameQuery(request.getStoreNameQuery()))
            .regionNames(emptyIfNull(request.getRegionNames()))
            .scoreRange(scoreRange)
            .orderMode(orderMode)
            .page(request.getPage())
            .size(request.getSize())
            .build();
    }

    private List<String> parseStoreNameQuery(String query) {
        String[] parts = query.split(" ");
        return Stream.of(parts)
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .toList();
    }

    private <T> List<T> emptyIfNull(List<T> list) {
        return Objects.requireNonNullElse(list, Collections.emptyList());
    }
}
