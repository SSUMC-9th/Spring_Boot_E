package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.dto.MissionResponseDto;
import com.example.demo.domain.mission.dto.MyMissionResponseDto;

import java.util.List;

//보조 인터페이스 (Custom Repository)
public interface MyMissionRepositoryCustom {
    List<MyMissionResponseDto> findOngoingMissionsByUser(Long userId, int page);

}
