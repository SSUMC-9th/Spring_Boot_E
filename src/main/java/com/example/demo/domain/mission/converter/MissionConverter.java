package com.example.demo.domain.mission.converter;

import com.example.demo.domain.mission.dto.MissionResponseDto;
import com.example.demo.domain.mission.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDto toDto(Mission mission) {
        return MissionResponseDto.builder()
                .missionId(mission.getId())
                .missionName(mission.getMissionName())
                .storeName(mission.getStore().getStoreName())
                .build();
    }

    public static List<MissionResponseDto> toDtoList(List<Mission> missions) {
        return missions.stream()
                .map(MissionConverter::toDto)
                .collect(Collectors.toList());
    }
}
