package com.umc_study.mission_server.review.service;

import com.umc_study.mission_server.common.Range;
import com.umc_study.mission_server.review.dto.ReviewSearchRequest;
import com.umc_study.mission_server.review.domain.Review;
import com.umc_study.mission_server.review.exception.ReviewErrorCode;
import com.umc_study.mission_server.review.exception.ReviewException;
import com.umc_study.mission_server.review.repository.ReviewRepository;
import com.umc_study.mission_server.review.domain.ReviewSearchQueries;
import com.umc_study.mission_server.review.domain.ReviewSearchQueries.ReviewSearchOrderMode;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

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
