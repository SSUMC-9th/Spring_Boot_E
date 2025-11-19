package com.example.umc9th.domain.member_mission.service.command;

import com.example.umc9th.domain.member_mission.entity.MemberMission;

public interface MemberMissionCommandService {
    MemberMission challengeMission(Long memberId, Long missionId);
}