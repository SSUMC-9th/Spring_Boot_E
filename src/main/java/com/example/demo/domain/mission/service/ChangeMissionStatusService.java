package com.example.demo.domain.mission.service;

import com.example.demo.domain.mission.dto.MissionRequest;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.store.entity.Store;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChangeMissionStatusService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;  // Store 검증을 위해 추가

    @Transactional
    public Long changeMissionStatus(Long storeId, Long missionId, MissionRequest missionRequest) {
        // 1. 가게 존재 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        // 2. 미션 존재 확인
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("미션을 찾을 수 없습니다."));

        // 3. 해당 미션이 이 가게의 미션인지 검증
        if (!mission.getStore().getId().equals(storeId)) {
            throw new IllegalArgumentException("해당 가게의 미션이 아닙니다.");
        }

        // 4. 미션 상태 변경 (도전 중으로)
        mission.setStatus(missionRequest.getMissionStatus());

        Mission savedMission = missionRepository.save(mission);

        return savedMission.getId();
    }
}