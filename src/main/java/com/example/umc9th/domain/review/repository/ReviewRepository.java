// com/example/umc9th/domain/review/repository/ReviewRepository.java
package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// JpaRepository<Review, Long> 뒤에 , ReviewRepositoryCustom 을 추가!
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom, ReviewQueryDsl {

    // 5주차 때 만든 메서드 (그대로)
    Page<Review> findAllByMember(Member member, Pageable pageable);
    Page<Review> findAllByStore(Store store, Pageable pageable);
    Page<Review> findByStoreIdAndRating(Long storeId, Integer rating, Pageable pageable);
    Page<Review> findByStoreId(Long storeId, Pageable pageable);
    Page<Review> findByMemberId(Long memberId, Pageable pageable);
}