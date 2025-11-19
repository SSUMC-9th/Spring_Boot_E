package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.umc9th.domain.store.entity.Store;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    // Repository 주입
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review writeReview(Long memberId, Long storeId, ReviewReqDTO.WriteReviewDTO dto) {

        // 1. 엔티티 존재 확인 (Validation)
        // 🚨 8주차 미션 요구사항: MemberId는 하드코딩된 값(1L) 사용
        // 실제로는 Member 엔티티가 필요하므로 Repository에서 조회해야 합니다.
        // ID 1번 유저가 없으면 에러가 날 수 있으니 DB에 1번 유저가 있는지 확인하세요.
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // Store 존재 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 2. Review 엔티티 생성 및 저장
        Review newReview = ReviewConverter.toReview(dto, member, store);

        return reviewRepository.save(newReview);
    }
}