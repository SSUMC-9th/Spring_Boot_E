package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

public class MissionResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreviewDTO {
        private Long missionId;
        private String storeName;       // 미션이 걸려있는 가게 이름
        private String missionCondition; // 미션 내용
        private Integer point;          // 제공 포인트
        private LocalDateTime createdAt;
    }
}