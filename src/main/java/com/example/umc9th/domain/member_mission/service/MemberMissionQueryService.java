// MemberMissionQueryService.java (Interface)
package com.example.umc9th.domain.member_mission.service;

import com.example.umc9th.domain.member_mission.entity.MemberMission;
import org.springframework.data.domain.Page;

public interface MemberMissionQueryService {
    // isComplete를 인수로 받아 진행 중(false)과 완료(true) 미션을 구분합니다.
    Page<MemberMission> getMemberMissionList(Long memberId, Boolean isComplete, Integer page);
}