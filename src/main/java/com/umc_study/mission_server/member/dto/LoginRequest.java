package com.umc_study.mission_server.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @Email
    String email,
    @NotBlank
    String password
) {
}
