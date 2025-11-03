package com.example.demo.domain.mission.service.Dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyMissionDto {
    private String missionName;
    private String storeAddress;
    private int missionPoint;
}
