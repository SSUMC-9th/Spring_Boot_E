package com.example.umc9th.domain.mission.service;

import ch.qos.logback.core.status.ErrorStatus;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository; // Repository import 필요
// import com.example.umc9th.domain.store.repository.StoreRepository; // Store 검증 시 필요
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Spring Bean으로 등록
@RequiredArgsConstructor // Repository 주입을 위해 필요
@Transactional(readOnly = true) // 조회 기능이므로 readOnly = true
public class MissionServiceImpl implements MissionService {

    // Repository 주입
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository; // Store 검증 시 주입

    @Override
    public Page<Mission> getStoreMissions(Long storeId, Pageable pageable) {
        // 1. Validation: Store 존재 여부 검증 활성화
        storeRepository.findById(storeId)
                // 💡 StoreErrorCode.NOT_FOUND는 실제 에러 코드 경로로 변경해야 합니다.
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 2. Repository 호출
        return missionRepository.findByStoreId(storeId, pageable);
    }
}