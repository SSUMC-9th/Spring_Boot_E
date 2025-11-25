package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions") // 지역 기반 조회이므로 /regions 경로에 붙이는 것이 자연스러움
public class MissionController {

    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    // [5주차 최종 쿼리] 홈 화면: 특정 지역의 도전 가능 미션 목록 조회 (JPQL 활용)
    // Home 화면 (예시: /regions/1/missions?page=0)
    @GetMapping("/{regionId}/missions")
    public ApiResponse<List<MissionResDTO.MissionPreviewDTO>> getMissionList(
            @PathVariable(name = "regionId") Long regionId,
            @RequestParam(name = "page", defaultValue = "0") Integer page // 페이징 처리
    ) {
        // Service 호출 (내부에서 JPQL 쿼리가 실행됨)
        Page<Mission> missionPage = missionQueryService.getMissionListByRegion(regionId, page);

        // Converter를 통해 DTO 변환 (페이징 정보는 DTO에 담겨 나감)
        List<MissionResDTO.MissionPreviewDTO> result = MissionConverter.toMissionPreviewListDTO(missionPage);

        return ApiResponse.onSuccess(result);
    }

    // [8주차 필수 미션] 가게에 미션 추가하기 API
    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.MissionPreviewDTO> addMission(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid MissionReqDTO.AddMissionDTO dto
    ) {
        // Service 호출
        Mission mission = missionCommandService.addMission(storeId, dto);

        // 응답 DTO 변환 (MissionConverter에 toMissionPreviewDTO 재활용)
        return ApiResponse.of(GeneralSuccessCode.CREATED, MissionConverter.toMissionPreviewDTO(mission));
    }
}