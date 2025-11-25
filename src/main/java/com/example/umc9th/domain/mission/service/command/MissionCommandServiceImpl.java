package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional // DB 쓰기 작업이므로 @Transactional 필수
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Mission addMission(Long storeId, MissionReqDTO.AddMissionDTO dto) {

        // 1. Store 엔티티 조회 (가게 존재 여부 확인)
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 2. Mission 엔티티 생성
        Mission newMission = MissionConverter.toMission(dto, store);

        // 3. DB 저장
        return missionRepository.save(newMission);
    }
}