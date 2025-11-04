package com.umc_study.mission_server.review.repository;

import com.umc_study.mission_server.review.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT DISTINCT r FROM Review r JOIN FETCH r.images")
    List<Review> findWithImagesAll();

    @Query("SELECT DISTINCT r FROM Review r WHERE r.store.id = :storeId")
    List<Review> findAllWithImagesByStoreId(@Param("storeId") Long storeId);
}
