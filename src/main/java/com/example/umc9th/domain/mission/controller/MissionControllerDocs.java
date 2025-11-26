package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface MissionControllerDocs {
    @Operation(
            summary = "특정 가게의 미션 목록 조회 API",
            description = "특정 가게(Store)에 등록된 모든 미션 목록을 페이지네이션으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게를 찾을 수 없음")
    })
    @GetMapping("/stores/{storeId}")
    ApiResponse<MissionResDTO.MissionListDTO> getStoreMissions(
            @PathVariable(name = "storeId") Long storeId,
            Pageable pageable
    );
    @Operation(
            summary = "회원의 진행 중인 미션 목록 조회 API",
            description = "특정 회원이 현재 수행 중인 미션 목록(is_complete = false)을 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    @GetMapping("/members/{memberId}/missions/in-progress")
    ApiResponse<MissionResDTO.MissionListDTO> getInProgressMissions(
            @PathVariable(name = "memberId") Long memberId,
            Pageable pageable
    );

}
