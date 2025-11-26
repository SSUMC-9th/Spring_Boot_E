// .../domain/member_mission/repository/MemberMissionRepository.java
package com.example.umc9th.domain.member_mission.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // "내가(Member) 진행중/진행 완료(isComplete)한 미션" 목록 조회 (페이징 포함)
    // 메서드 이름(findAllByMemberAndIsComplete)만으로 쿼리가 생성됩니다.
    Page<MemberMission> findAllByMemberAndIsComplete(Member member, Boolean isComplete, Pageable pageable);
    Page<MemberMission> findByMemberIdAndIsComplete(Long memberId, Boolean isComplete, Pageable pageable);
}