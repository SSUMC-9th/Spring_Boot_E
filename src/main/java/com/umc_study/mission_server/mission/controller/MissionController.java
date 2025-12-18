package com.umc_study.mission_server.mission.controller;

import com.umc_study.mission_server.common.response.ApiResponse;
import com.umc_study.mission_server.mission.dto.CreateMissionRequest;
import com.umc_study.mission_server.mission.dto.MissionListResponse;
import com.umc_study.mission_server.mission.dto.MissionResponse;
import com.umc_study.mission_server.mission.entity.Mission;
import com.umc_study.mission_server.mission.service.MissionService;
import com.umc_study.mission_server.review.dto.ReviewListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @GetMapping("/stores/{storeId}/missions")
    @Operation(
        summary = "가게의 미션 목록",
        description = "특정 가게에 등록된 미션들의 목록을 조회합니다. 페이지네이션 제공"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "찾을 수 없는 가게")
    })
    public ApiResponse<MissionListResponse> getMissionListByStoreId(
        @PathVariable Long storeId,
        @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 15);
        MissionListResponse list = missionService.getMissionListByStoreId(storeId, pageRequest);
        return ApiResponse.ok(list);
    }

    @PostMapping("/missions")
    @Operation(
        summary = "미션 생성"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    })
    public ApiResponse<MissionResponse> createMission(
        @RequestBody CreateMissionRequest request
    ) {
        Mission mission = missionService.create(request);
        return ApiResponse.ok(MissionResponse.from(mission));
    }

    @GetMapping("/missions/my")
    @Operation(
        summary = "내가 진행중인 미션 목록",
        description = "나에게 등록된 미션 목록을 조회합니다. 페이지네이션 제공"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    })
    public ApiResponse<MissionListResponse> getMyMissionList(
        @RequestParam Long memberId,
        @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 15);
        MissionListResponse list = missionService.getMissionListByMemberId(memberId, pageRequest);
        return ApiResponse.ok(list);
    }

    @PatchMapping("/missions/{id}/start")
    @Operation(
        summary = "미션 시작 처리"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    })
    public ApiResponse<MissionResponse> startMission(
        @PathVariable Long id
    ) {
        Mission mission = missionService.start(id);
        return ApiResponse.ok(MissionResponse.from(mission));
    }

    @PatchMapping("/missions/{id}/success")
    @Operation(
        summary = "미션 성공 처리"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공")
    })
    public ApiResponse<MissionResponse> successMission(
        @PathVariable Long id
    ) {
        Mission mission = missionService.success(id);
        return ApiResponse.ok(MissionResponse.from(mission));
    }
}
