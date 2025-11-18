package com.umc_study.mission_server.mission.dto;

import java.time.LocalDateTime;

public record CreateMissionRequest(
    Long storeId,
    Long memberId,
    Long point,
    Long money,
    LocalDateTime due
) {
}
