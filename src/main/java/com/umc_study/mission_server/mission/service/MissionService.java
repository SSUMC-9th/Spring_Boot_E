package com.umc_study.mission_server.mission.service;

import com.umc_study.mission_server.member.entity.Member;
import com.umc_study.mission_server.member.exception.MemberErrorCode;
import com.umc_study.mission_server.member.exception.MemberException;
import com.umc_study.mission_server.member.repository.MemberRepository;
import com.umc_study.mission_server.mission.dto.CreateMissionRequest;
import com.umc_study.mission_server.mission.dto.MissionListResponse;
import com.umc_study.mission_server.mission.entity.Mission;
import com.umc_study.mission_server.mission.entity.MissionState;
import com.umc_study.mission_server.mission.exception.MissionErrorCode;
import com.umc_study.mission_server.mission.exception.MissionException;
import com.umc_study.mission_server.mission.repository.MissionRepository;
import com.umc_study.mission_server.store.entity.Store;
import com.umc_study.mission_server.store.exception.StoreErrorCode;
import com.umc_study.mission_server.store.exception.StoreException;
import com.umc_study.mission_server.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public Mission create(CreateMissionRequest request) {
        Store store = storeRepository.findById(request.storeId())
            .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Member member = memberRepository.findById(request.memberId())
            .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Mission mission = Mission.builder()
            .point(request.point())
            .money(request.money())
            .due(request.due())
            .state(MissionState.PENDING)
            .store(store)
            .member(member)
            .build();
        missionRepository.save(mission);
        return mission;
    }

    @Transactional
    public Mission start(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));
        if (mission.getState() == MissionState.PENDING) {
            throw new MissionException(MissionErrorCode.INVALID_STATE_EXPECT_PENDING);
        }
        mission.setState(MissionState.PROGRESS);
        return missionRepository.save(mission);
    }

    @Transactional
    public Mission success(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));
        if (mission.getState() == MissionState.PROGRESS) {
            throw new MissionException(MissionErrorCode.INVALID_STATE_EXPECT_PROGRESS);
        }
        mission.setState(MissionState.SUCCESS);
        return missionRepository.save(mission);
    }

    public MissionListResponse getMissionListByStoreId(Long storeId, Pageable pageable) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Page<Mission> page = missionRepository.findAllByStoreId(storeId, pageable);
        return MissionListResponse.from(page);
    }

    public MissionListResponse getMissionListByMemberId(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Page<Mission> page = missionRepository.findAllByMemberId(memberId, pageable);
        return MissionListResponse.from(page);
    }
}
