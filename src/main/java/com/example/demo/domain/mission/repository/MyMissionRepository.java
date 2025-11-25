package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

//메인 인터페이스 선언
public interface MyMissionRepository extends JpaRepository<Mission, Long>, MyMissionRepositoryCustom {
}
