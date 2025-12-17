package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.MyMissionResponseDto;
import com.example.demo.domain.mission.service.MyMissionService;
import com.example.demo.global.apiPayload.exception.ValidPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/my_mission")
@RequiredArgsConstructor
public class MyMissionController {

    private final MyMissionService myMissionService;

    @GetMapping
//    @Operation(summary = "내 진행중인 미션 목록 조회", description = "사용자가 진행중인 미션을 페이징 처리하여 조회합니다.")
    public List<MyMissionResponseDto> getOngoingMissions(@ValidPage Integer page) {
        Long userId = 1L; // 실제로는 인증된 사용자 ID 가져오기
        return myMissionService.getOngoingMissions(userId, page);
    }
}
