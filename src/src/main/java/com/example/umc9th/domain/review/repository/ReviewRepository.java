package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.member.entity.Member; // 👈 Member 임포트
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List; // 👈 List 임포트

public interface ReviewRepository extends JpaRepository<Review, Long> {


    // "Review 엔티티에서 member 필드를 기준으로 조회한다"
    List<Review> findByMember(Member member);
}