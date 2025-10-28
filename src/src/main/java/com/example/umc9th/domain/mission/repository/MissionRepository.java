package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.location.entity.Location;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 그 s.location이 :location 파라미터와 같은 것들을 페이징 처리
    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.location = :location")
    Page<Mission> findMissionsByLocation(@Param("location") Location location, Pageable pageable);
}