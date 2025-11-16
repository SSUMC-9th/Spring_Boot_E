package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.entity.Mission;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface MissionQueryDsl {
    List<Mission> ServiceMission(Predicate predicate);
}

