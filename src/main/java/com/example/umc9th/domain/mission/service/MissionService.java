package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionService {
    Page<Mission> getStoreMissions(Long storeId, Pageable pageable);
}
