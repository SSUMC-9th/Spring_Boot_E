package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.dto.MissionResponseDto;

import java.util.List;

public interface MissionRepositoryCustom {
    List<MissionResponseDto> findMissionsByStore(Long storeId, int page);
}
