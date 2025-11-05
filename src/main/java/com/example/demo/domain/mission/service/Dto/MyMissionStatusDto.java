package com.example.demo.domain.mission.service.Dto;

import com.example.demo.domain.mission.enums.MissionStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyMissionStatusDto {
    private String missionName;
    private MissionStatus status;
    private int missionPoint;
}
