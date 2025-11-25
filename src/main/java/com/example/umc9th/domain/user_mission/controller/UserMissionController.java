package com.example.umc9th.domain.user_mission.controller;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.user_mission.service.UserMissionQueryService;
import com.example.umc9th.global.api.ApiResponse;
import com.example.umc9th.global.validation.annotation.CheckPage; // 커스텀 검증 어노테이션
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.umc9th.domain.user_mission.entity.UserMission;

@RestController
@RequiredArgsConstructor
@Validated // 검증 활성화
@RequestMapping("/missions")
public class UserMissionController {

    private final UserMissionQueryService userMissionQueryService;

    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "진행중인 미션들을 조회합니다. 페이징 포함.")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호 (1 이상)")
    })
    @GetMapping("/my-missions")
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMyMissions(
            @RequestParam(name = "userId") Long userId,
            @CheckPage @RequestParam(name = "page") Integer page // 커스텀 검증
    ) {
        Page<UserMission> missionPage = userMissionQueryService.getMyMissionList(userId, page);

        return ApiResponse.onSuccess(MissionConverter.toMissionPreViewListDTO(missionPage));
    }
}