package com.umc_study.mission_server.member.dto;

import lombok.Builder;

@Builder
public record LoginResponse(
    Long memberId,
    String accessToken
) {
}
