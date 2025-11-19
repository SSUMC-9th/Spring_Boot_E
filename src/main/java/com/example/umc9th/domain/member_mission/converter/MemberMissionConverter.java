package com.example.umc9th.domain.member_mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member_mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberMissionResDTO.MemberMissionPreviewDTO toMemberMissionPreviewDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission(); // MemberMission에서 Mission 엔티티를 가져옵니다.

        return MemberMissionResDTO.MemberMissionPreviewDTO.builder()
                .memberMissionId(memberMission.getId())
                .storeName(mission.getStore().getStoreName())
                .missionCondition(mission.getConditinal()) // Mission Entity의 필드명에 따라 수정하세요.
                .point(mission.getPoint())
                .isComplete(memberMission.getIsComplete())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    // Page 객체와 DTO 리스트를 결합하여 최종 응답 DTO로 변환
    public static MemberMissionResDTO.MemberMissionListDTO toMemberMissionListDTO(Page<MemberMission> memberMissionPage) {

        List<MemberMissionResDTO.MemberMissionPreviewDTO> memberMissionPreviewList = memberMissionPage.stream()
                .map(MemberMissionConverter::toMemberMissionPreviewDTO)
                .collect(Collectors.toList());

        return MemberMissionResDTO.MemberMissionListDTO.builder()
                .memberMissionList(memberMissionPreviewList)
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .listSize(memberMissionPreviewList.size())
                .build();
    }
    // DTO -> Entity 변환 (Challenge)
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false) // 👈 도전 중 상태이므로 false로 초기화
                .build();
    }

    // Entity -> DTO 변환 (Challenge 응답)
    public static MemberMissionResDTO.ChallengeMissionDTO toChallengeMissionDTO(MemberMission memberMission) {
        return MemberMissionResDTO.ChallengeMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .challengedAt(memberMission.getCreatedAt())
                .build();
    }
}