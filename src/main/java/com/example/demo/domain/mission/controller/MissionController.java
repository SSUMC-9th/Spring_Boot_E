package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.MissionRequest;
import com.example.demo.domain.mission.dto.MissionResponse;
import com.example.demo.domain.mission.dto.MissionResponseDto;
import com.example.demo.domain.mission.service.ChangeMissionStatusService;
import com.example.demo.domain.mission.service.Dto.MyMissionDto;
import com.example.demo.domain.mission.service.MissionService;
import com.example.demo.global.apiPayload.exception.ValidPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;
    private final ChangeMissionStatusService changeMissionStatusService;

    @GetMapping
    public Page<MyMissionDto> getMissions(@RequestParam String storeAddress,
                                          @RequestParam int page,
                                          @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionService.getMissionsByAddress(storeAddress, pageable);
    }

    // 가게의 미션을 도전 중인 미션에 추가
    @PostMapping("/{storeId}/{missionId}")
    public ResponseEntity<MissionResponse> challengeMission(
            @PathVariable Long storeId,
            @PathVariable Long missionId,
            @RequestBody MissionRequest request) {

        Long resultMissionId = changeMissionStatusService.changeMissionStatus(storeId, missionId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MissionResponse(resultMissionId, "미션 도전 성공"));
    }

    @GetMapping("/{storeId}")
//    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 미션을 페이징 처리하여 조회합니다.")
    public List<MissionResponseDto> getStoreMissions(@PathVariable Long storeId,
                                                     @ValidPage Integer page) {
        return missionService.getStoreMissions(storeId, page);
    }

}