package com.umc_study.mission_server.review.repository;

import com.umc_study.mission_server.review.entity.Review;
import com.umc_study.mission_server.store.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT DISTINCT r FROM Review r JOIN FETCH r.images")
    List<Review> findAll();

    @Query("SELECT DISTINCT r FROM Review r JOIN FETCH r.images")
    List<Review> findAllByStore(Store store);
}
