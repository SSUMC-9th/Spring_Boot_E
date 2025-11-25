package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.entity.Review;
import java.util.List;

public interface ReviewQueryService {
    // Controller에서 받은 검색 키워드(query)와 검색 타입(type)을 기반으로 검색 결과를 반환합니다.
    List<Review> searchReview(String query, String type);
}