package com.example.demo.domain.mission.dto;

import com.example.demo.domain.mission.enums.MissionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionRequest {
    // id는 보통 PathVariable로 받으므로 제거 고려
    private MissionStatus missionStatus;
    // store는 storeId로 받는 것이 일반적
    private Long storeId;
}