package com.example.demo.domain.mission.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MissionResponse {
    private Long missionId;
    private String message;

    public MissionResponse(Long missionId, String message) {
        this.missionId = missionId;
        this.message = message;
    }
}
