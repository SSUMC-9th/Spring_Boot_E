package com.umc_study.mission_server.review.repository;

import com.umc_study.mission_server.review.domain.Review;
import com.umc_study.mission_server.review.domain.ReviewSearchQueries;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryDsl {
    List<Review> search(ReviewSearchQueries queries);
    Page<Review> findAllByStoreId(Long storeId, Pageable pageable);
    Page<Review> findAllByMemberId(Long memberId, Pageable pageable);
}
