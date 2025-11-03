package com.example.demo.domain.mission.controller;

import com.example.demo.domain.member.enums.Address;
import com.example.demo.domain.mission.service.Dto.MyMissionDto;
import com.example.demo.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public Page<MyMissionDto> getMissions(@RequestParam String storeAddress,
                                          @RequestParam int page,
                                          @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionService.getMissionsByAddress(storeAddress, pageable);
    }
}
