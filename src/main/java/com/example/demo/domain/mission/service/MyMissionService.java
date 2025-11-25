package com.example.demo.domain.mission.service;

import com.example.demo.domain.mission.dto.MyMissionResponseDto;
import com.example.demo.domain.mission.repository.MyMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyMissionService {

    private final MyMissionRepository myMissionRepository;

    public List<MyMissionResponseDto> getOngoingMissions(Long userId, int page) {
        List<MyMissionResponseDto> missions = myMissionRepository.findOngoingMissionsByUser(userId, page);

        // Stream + Builder 패턴 활용
        return missions.stream()
                .map(m -> MyMissionResponseDto.builder()
                        .missionId(m.getMissionId())
                        .missionName(m.getMissionName())
                        .storeName(m.getStoreName())
                        .status(m.getStatus())
                        .build())
                .toList();
    }
}
