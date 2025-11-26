package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionQueryService {
    Page<Mission> getMissionListByRegion(Long regionId, Integer page);
    Page<MemberMission> getInProgressMissions(Long memberId, Pageable pageable);
}