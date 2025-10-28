package com.example.demo.domain.mission.service;

import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.enums.MissionStatus;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.mission.service.Dto.MyMissionStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionStatusService {

    private final MissionRepository missionRepository;

    public Page<MyMissionStatusDto> getMyMissionsStatus(List<MissionStatus> statuses, Pageable pageable) {
        List<MissionStatus> missionStatuses = List.of(MissionStatus.IN_PROGRESS, MissionStatus.COMPLETED);

        Page<Mission> missions = missionRepository.findByStatusIn(missionStatuses, pageable);

        return missions.map(mission -> MyMissionStatusDto.builder()
                .missionName(mission.getMissionName())
                .status(mission.getStatus())
                .missionPoint(mission.getMissionPoint())
                .build());
    }
}
