package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 9주차 미션: 특정 가게의 미션 목록 조회 (페이징)
    // SELECT * FROM mission WHERE store_id = ?
    Page<Mission> findAllByStore(Store store, Pageable pageable);
}