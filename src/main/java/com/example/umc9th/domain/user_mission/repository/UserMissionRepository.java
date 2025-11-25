package com.example.umc9th.domain.user_mission.repository;

import com.example.umc9th.domain.user.entity.User;
// 👇 여기가 중요합니다. UserMission 클래스가 있는 올바른 경로!
import com.example.umc9th.domain.user_mission.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 9주차 미션: 내가 진행중/완료한 미션 목록 조회 (페이징)
    // SELECT * FROM user_mission WHERE user_id = ? AND complete = ?
    Page<UserMission> findAllByUserAndComplete(User user, Boolean complete, Pageable pageable);
}