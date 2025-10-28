package com.umc_study.mission_server.mission.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.umc_study.mission_server.mission.entity.Mission;
import com.umc_study.mission_server.mission.entity.MissionState;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    
    @EntityGraph(value = "Mission.withStore")
    @Query("SELECT m FROM Mission m WHERE m.id = :id")
    Optional<Mission> findByIdWithStore(@Param("id") Long id);

    @EntityGraph(value = "Mission.withStore")
    @Query("SELECT m FROM Mission m WHERE m.member.id = :memberId")
    List<Mission> findAllWithStoreByMemberId(@Param("memberId") Long memberId);

    @EntityGraph(value = "Mission.withStore")
    @Query("SELECT m FROM Mission m WHERE m.member.id = :memberId")
    Page<Mission> findAllWithStoreByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    @EntityGraph(value = "Mission.withStore")
    @Query("SELECT m FROM Mission m WHERE m.state = :state AND m.store.address1 = :address")
    Page<Mission> findAllWithStoreByStateAndAddress(
        @Param("state") MissionState state, 
        @Param("address") String address, 
        Pageable pageable);
}
