package com.umc_study.mission_server.store.controller;

import com.umc_study.mission_server.common.response.ApiResponse;
import com.umc_study.mission_server.store.dto.UpdateStoreLocationRequest;
import com.umc_study.mission_server.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PatchMapping("/stores/{storeId}/location")
    public ApiResponse<?> updateLocation(
        @PathVariable Long storeId,
        @RequestBody UpdateStoreLocationRequest request
    ) {
        storeService.updateStoreLocation(storeId, request.location());
        return ApiResponse.ok(null);
    }
}
