package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.dto.MissionResponseDto;
import com.example.demo.domain.mission.entity.QMission;
import com.example.demo.domain.mission.repository.MissionRepositoryCustom;
import com.example.demo.domain.store.entity.QStore;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MissionResponseDto> findMissionsByStore(Long storeId, int page) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        return queryFactory
                .select(Projections.constructor(MissionResponseDto.class,
                        mission.id,
                        mission.missionName,
                        store.storeName))
                .from(mission)
                .join(store).on(mission.id.eq(store.id.intValue()))
                .where(store.id.eq(storeId))
                .offset((page - 1) * 10)
                .limit(10)
                .fetch();
    }
}
