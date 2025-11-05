package com.example.demo.domain.mission.repository;

import com.example.demo.domain.member.enums.Address;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findByStoreStoreAddress(String storeAddress, Pageable pageable);
    Page<Mission> findByStatusIn(List<MissionStatus> statuses, Pageable pageable);
}

