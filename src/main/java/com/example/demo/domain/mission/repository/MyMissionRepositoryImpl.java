package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.dto.MyMissionResponseDto;
import com.example.demo.domain.mission.entity.QMission;
import com.example.demo.domain.mission.enums.MissionStatus;
import com.example.demo.domain.store.entity.QStore;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MyMissionRepositoryImpl implements MyMissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MyMissionResponseDto> findOngoingMissionsByUser(Long userId, int page) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        return queryFactory
                .select(Projections.constructor(MyMissionResponseDto.class,
                        mission.id,
                        mission.missionName,
                        store.storeName,
                        mission.status))
                .from(mission)
                .join(store).on(mission.id.eq(store.id.intValue()))
                .where(mission.id.eq(Math.toIntExact(userId))
                        .and(mission.status.eq(MissionStatus.valueOf("IN_PROGRESS"))))
                .offset((page - 1) * 10)
                .limit(10)
                .fetch();
    }
}
