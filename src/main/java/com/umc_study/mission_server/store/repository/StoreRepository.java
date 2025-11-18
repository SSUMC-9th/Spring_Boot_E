package com.umc_study.mission_server.store.repository;

import com.umc_study.mission_server.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
