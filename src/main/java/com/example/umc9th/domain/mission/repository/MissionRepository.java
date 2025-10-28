// .../domain/mission/repository/MissionRepository.java
package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.region.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // "홈 화면 - 현재 지역의 도전 가능 미션" 목록 조회 (페이징 포함)
    // JPQL을 사용해 Mission(m)과 Store(s)를 JOIN합니다.
    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.region = :region")
    Page<Mission> findMissionsByRegion(@Param("region") Region region, Pageable pageable);
}