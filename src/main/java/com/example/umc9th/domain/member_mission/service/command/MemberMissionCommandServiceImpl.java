package com.example.umc9th.domain.member_mission.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.member_mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.member_mission.repository.MemberMissionRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMission challengeMission(Long memberId, Long missionId) {

        // 1. 엔티티 존재 확인 및 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 2. MemberMission 엔티티 생성
        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);

        // 3. DB 저장 (도전 중 상태로 저장)
        return memberMissionRepository.save(memberMission);
    }
}