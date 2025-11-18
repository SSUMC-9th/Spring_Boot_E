package com.umc_study.mission_server.member.dto;

import com.umc_study.mission_server.common.annotation.ExistsFoodType;
import java.util.List;

public record UpdatePreferFoodTypesRequest(
    Long memberId,
    @ExistsFoodType
    List<Long> foodTypes
) {
}
