package com.example.umc9th.domain.member_mission.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // "Member 필드와 complete 필드를 기준으로 조회하며, 페이징 처리를 한다"
    Page<MemberMission> findByMemberAndComplete(Member member, Boolean complete, Pageable pageable);
}