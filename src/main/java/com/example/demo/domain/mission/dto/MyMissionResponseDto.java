package com.example.demo.domain.mission.dto;

import com.example.demo.domain.mission.enums.MissionStatus;
import com.example.demo.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyMissionResponseDto {
    private Long missionId;
    private String missionPoint;
    private String missionName;
    private String storeName;
    private String status;
}

