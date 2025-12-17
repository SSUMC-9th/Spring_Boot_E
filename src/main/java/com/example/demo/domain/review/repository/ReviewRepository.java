package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//메인 인터페이스 선언
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl, ReviewRepositoryCustom {
    //작성쿼리는 save()로 처리하므로 별도 메서드 필요없음

}