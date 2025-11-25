package com.example.umc9th.domain.member_mission.controller;

import com.example.umc9th.domain.member_mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.member_mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.member_mission.service.MemberMissionQueryService;
import com.example.umc9th.domain.member_mission.service.command.MemberMissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/missions") // API 경로
public class MemberMissionController {

    private final MemberMissionQueryService memberMissionQueryService;
    private final MemberMissionCommandService memberMissionCommandService;
    // 회원의 미션 목록 조회 (진행 중 또는 완료)
    // 예시 호출:
    // - 진행 중 미션: GET /members/missions?memberId=1&isComplete=false&page=0
    // - 완료된 미션: GET /members/missions?memberId=1&isComplete=true&page=0
    @GetMapping("")
    public ApiResponse<MemberMissionResDTO.MemberMissionListDTO> getMemberMissionList(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "isComplete") Boolean isComplete, // 진행 상태
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {

        // 1. Service 호출
        Page<MemberMission> memberMissionPage = memberMissionQueryService.getMemberMissionList(memberId, isComplete, page);

        // 2. DTO 변환 및 반환
        MemberMissionResDTO.MemberMissionListDTO result = MemberMissionConverter.toMemberMissionListDTO(memberMissionPage);

        return ApiResponse.onSuccess(result);
    }

    // [8주차 필수 미션] 가게의 미션을 도전 중인 미션에 추가 (미션 도전하기) API
// 3주차 URL 설계: POST /missions/{missionId}/challenge
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MemberMissionResDTO.ChallengeMissionDTO> challengeMission(
            @PathVariable(name = "missionId") Long missionId
    ) {
        // 🚨 8주차 미션 요구사항: MemberId는 하드코딩된 값(1L) 사용
        Long memberId = 1L;

        // Service 호출 및 저장
        MemberMission memberMission = memberMissionCommandService.challengeMission(memberId, missionId);

        // 응답 DTO 변환 및 HTTP 201 Created 코드로 반환
        return ApiResponse.of(GeneralSuccessCode.CREATED, MemberMissionConverter.toChallengeMissionDTO(memberMission));
    }
}