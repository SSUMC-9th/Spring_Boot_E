package com.umc_study.mission_server.mission.dto;

import com.umc_study.mission_server.mission.entity.Mission;
import java.util.List;
import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder
public record MissionListResponse(
    List<MissionResponse> missionList,
    int listSize,
    int totalPage,
    long totalElements,
    boolean isFirst,
    boolean isLast
) {
    public static MissionListResponse from(Page<Mission> page) {
        return MissionListResponse.builder()
            .missionList(page.stream()
                .map(MissionResponse::from)
                .toList())
            .listSize(page.getSize())
            .totalPage(page.getTotalPages())
            .totalElements(page.getTotalElements())
            .isFirst(page.isFirst())
            .isLast(page.isLast())
            .build();
    }
}
