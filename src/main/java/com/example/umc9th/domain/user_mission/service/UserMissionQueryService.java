package com.example.umc9th.domain.user_mission.service;

import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user_mission.entity.UserMission;
import com.example.umc9th.domain.user_mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.umc9th.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    public Page<UserMission> getMyMissionList(Long userId, Integer page) {
        // 1. 유저 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        // 2. 페이징 설정 (0부터 시작하므로 -1)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        // 3. 진행중인 미션만 조회 (complete = false)
        return userMissionRepository.findAllByUserAndComplete(user, false, pageRequest);
    }
}