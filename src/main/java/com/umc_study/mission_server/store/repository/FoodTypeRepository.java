package com.umc_study.mission_server.store.repository;

import com.umc_study.mission_server.store.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {
}
