package com.umc_study.mission_server.mission.controller;

import com.umc_study.mission_server.common.response.ApiResponse;
import com.umc_study.mission_server.mission.dto.CreateMissionRequest;
import com.umc_study.mission_server.mission.dto.MissionResponse;
import com.umc_study.mission_server.mission.entity.Mission;
import com.umc_study.mission_server.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @PostMapping("/missions")
    public ApiResponse<MissionResponse> createMission(
        @RequestBody CreateMissionRequest request
    ) {
        Mission mission = missionService.create(request);
        return ApiResponse.ok(MissionResponse.from(mission));
    }

    @PatchMapping("/missions/{id}/start")
    public ApiResponse<MissionResponse> startMission(
        @PathVariable Long id
    ) {
        Mission mission = missionService.start(id);
        return ApiResponse.ok(MissionResponse.from(mission));
    }
}
