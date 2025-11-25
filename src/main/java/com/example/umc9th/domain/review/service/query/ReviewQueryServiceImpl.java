package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.QReview; // Q클래스 임포트
import com.example.umc9th.domain.review.repository.ReviewQueryDsl;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    // Repository는 QueryDSL 기능이 추가된 인터페이스(ReviewRepository)를 주입합니다.
    private final ReviewQueryDsl reviewRepository;

    @Override
    public List<Review> searchReview(String query, String type) {

        // 1. Q클래스 정의
        QReview review = QReview.review;

        // 2. BooleanBuilder 정의 (동적 쿼리 조립 도구)
        BooleanBuilder builder = new BooleanBuilder();

        // 3. BooleanBuilder 사용 (동적 조건 조립)
        if (type.equals("location")) {
            // 지역 이름으로 검색하는 경우 (ERD 상의 regionName 필드 사용 가정)
            builder.and(review.store.region.regionName.contains(query));
        } else if (type.equals("star")) {
            // 별점 필터링 (쿼리 문자열을 Integer로 변환하여 사용)
            // 예시 이미지의 로직을 단순화하여 rating 이상으로 필터링
            builder.and(review.rating.goe(Integer.parseInt(query)));
        } else if (type.equals("both")) {
            // 지역 & 별점 동시 검색 (예시 이미지의 로직 반영: "안암동&4.5" 형태)
            String[] queries = query.split("&");

            // 안전하게 배열 크기 확인 후 사용 (실제 코드에서는 예외 처리 필요)
            if (queries.length == 2) {
                String locationQuery = queries[0];
                String starQuery = queries[1];

                builder.and(review.store.region.regionName.contains(locationQuery));
                builder.and(review.rating.goe(Integer.parseInt(starQuery)));
            }
        }

        // 4. Repository로 조립된 WHERE 절(builder)을 전달하여 쿼리 실행
        return reviewRepository.searchReview(builder);
    }
}