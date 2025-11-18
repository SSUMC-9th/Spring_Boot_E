package com.umc_study.mission_server.mission.dto;

import com.umc_study.mission_server.mission.entity.Mission;
import com.umc_study.mission_server.mission.entity.MissionState;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record MissionResponse(
    Long id,
    Long point,
    Long money,
    LocalDateTime due,
    MissionState state,
    Long storeId,
    Long memberId
) {
    public static MissionResponse from(Mission mission) {
        return MissionResponse.builder()
            .id(mission.getId())
            .point(mission.getPoint())
            .money(mission.getMoney())
            .due(mission.getDue())
            .state(mission.getState())
            .storeId(mission.getStore().getId())
            .memberId(mission.getMember().getId())
            .build();
    }
}
