package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.user.entity.User; // 👈 User import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository + Custom 둘 다 상속
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    // 9주차 미션: 내가 작성한 리뷰 목록 조회 (단순 페이징)
    // QueryDSL 없이 Spring Data JPA 기능만으로 조회
    Page<Review> findAllByUser(User user, Pageable pageable);
}