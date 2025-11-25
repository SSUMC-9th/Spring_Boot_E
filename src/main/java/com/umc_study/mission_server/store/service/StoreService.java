package com.umc_study.mission_server.store.service;

import com.umc_study.mission_server.common.response.GeneralException;
import com.umc_study.mission_server.store.entity.Store;
import com.umc_study.mission_server.store.exception.StoreErrorCode;
import com.umc_study.mission_server.store.exception.StoreException;
import com.umc_study.mission_server.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    @Transactional
    public void updateStoreLocation(Long storeId, String storeLocation) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        store.setAddress1(storeLocation);
    }
}
