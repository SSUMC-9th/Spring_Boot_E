package com.example.demo.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionResponseDto {
    private Long missionId;
    private String missionName;
    private String storeName;
}
