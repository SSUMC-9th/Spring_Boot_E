// MemberMissionQueryServiceImpl.java (Implementation)
package com.example.umc9th.domain.member_mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.member_mission.repository.MemberMissionRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository; // Member를 찾기 위해 필요

    @Override
    public Page<MemberMission> getMemberMissionList(Long memberId, Boolean isComplete, Integer page) {

        // 1. Member 엔티티 조회 (회원 존재 여부 확인)
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 2. 페이징 설정 (페이지당 10개씩)
        PageRequest pageRequest = PageRequest.of(page, 10);

        // 3. Query Method 호출 (회원과 미션 완료 여부에 따라 필터링)
        return memberMissionRepository.findAllByMemberAndIsComplete(member, isComplete, pageRequest);
    }
}