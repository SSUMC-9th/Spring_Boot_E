package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.entity.Mission;

public interface MissionCommandService {
    Mission addMission(Long storeId, MissionReqDTO.AddMissionDTO dto);
}
