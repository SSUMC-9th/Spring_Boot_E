package com.example.umc9th.domain.mission.dto.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class MissionReqDTO {

    @Getter
    public static class AddMissionDTO {
        // 미션 조건 (ex. 10000원 이상 구매 시...)
        @NotBlank(message = "미션 조건은 필수입니다.")
        private String missionCondition;

        // 포인트는 0점 이상이어야 함
        @Min(value = 0, message = "포인트는 0점 이상이어야 합니다.")
        private Integer point;
    }
}