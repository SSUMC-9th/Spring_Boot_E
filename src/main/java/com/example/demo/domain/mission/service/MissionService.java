package com.example.demo.domain.mission.service;

import com.example.demo.domain.member.enums.Address;
import com.example.demo.domain.mission.dto.MissionResponseDto;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.mapping.MemberMission;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.mission.service.Dto.MyMissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public Page<MyMissionDto> getMissionsByAddress(String storeAddress, Pageable pageable) {
        Page<Mission> missions = missionRepository.findByStoreStoreAddress(storeAddress, pageable);

        return missions.map(mission -> MyMissionDto.builder()
                .missionName(mission.getMissionName())
                .storeAddress(mission.getStore().getStoreAddress())
                .missionPoint(mission.getMissionPoint())
                .build());
    }

    public List<MissionResponseDto> getStoreMissions(Long storeId, int page) {
        List<MissionResponseDto> missions = missionRepository.findMissionsByStore(storeId, page);

        // Stream + Builder 패턴 활용
        return missions.stream()
                .map(m -> MissionResponseDto.builder()
                        .missionId(m.getMissionId())
                        .missionName(m.getMissionName())
                        .storeName(m.getStoreName())
                        .build())
                .toList();
    }
}
