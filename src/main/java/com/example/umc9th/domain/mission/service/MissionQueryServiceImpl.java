package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.region.entity.Region;
import com.example.umc9th.domain.region.repository.RegionRepository;
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
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final RegionRepository regionRepository; // Region을 찾기 위해 필요

    @Override
    public Page<Mission> getMissionListByRegion(Long regionId, Integer page) {

        // 1. Region 엔티티 조회 (없으면 에러 처리)
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 2. 페이징 설정
        PageRequest pageRequest = PageRequest.of(page, 10);

        // 3. JPQL 메서드 호출
        return missionRepository.findMissionsByRegion(region, pageRequest);
    }
}
