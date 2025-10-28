// .../domain/review/repository/ReviewRepository.java
package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // "리뷰 작성" 기능은 JpaRepository에 이미 있는 save() 메서드를 사용
    Page<Review> findAllByMember(Member member, Pageable pageable);
}