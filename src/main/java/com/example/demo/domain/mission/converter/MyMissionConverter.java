package com.example.demo.domain.mission.converter;

import com.example.demo.domain.mission.dto.MyMissionResponseDto;
import com.example.demo.domain.mission.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class MyMissionConverter {

    public static MyMissionResponseDto toDto(Mission mission) {
        return MyMissionResponseDto.builder()
                .missionId(mission.getId())
                .missionName(mission.getMissionName())
                .storeName(mission.getStore().getStoreName())
                .status(String.valueOf(mission.getStatus()))
                .build();
    }

    public static List<MyMissionResponseDto> toDtoList(List<Mission> missions) {
        return missions.stream()
                .map(MyMissionConverter::toDto)
                .collect(Collectors.toList());
    }
}
