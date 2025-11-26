// ReviewServiceImpl.java (구현 클래스)
package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 조회 기능이므로 readOnly = true
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public Page<Review> getMyReviews(Long memberId, Long storeId, Integer starRating, Pageable pageable) {

        // Repository 계층에 만들어둔 동적 쿼리 메서드를 그대로 호출합니다.
        return reviewRepository.findMyReviews(memberId, storeId, starRating, pageable);
    }
    @Override
    public Page<Review> getStoreReviews(Long storeId, Integer starRating, Pageable pageable) {
        // ⭐ starRating이 null이 아닐 경우 (별점 필터링 O)
        if (starRating != null) {
            return reviewRepository.findByStoreIdAndRating(storeId, starRating, pageable);
        }
        // ⭐ starRating이 null일 경우 (별점 필터링 X, 가게 전체 리뷰 조회)
        else {
            // 가게 ID로만 조회하는 메서드가 ReviewRepository에 정의되어 있어야 합니다.
            // (예: findByStoreId)
            return reviewRepository.findByStoreId(storeId, pageable);
        }
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByStoreName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }
    @Override
    public Page<Review> getMyReviews(Long memberId, Pageable pageable) {
        // Repository에 findByMemberId 쿼리 메서드를 호출하여 리뷰 목록을 가져옵니다.
        return reviewRepository.findByMemberId(memberId, pageable);
    }

}