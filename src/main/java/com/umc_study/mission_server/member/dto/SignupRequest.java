package com.umc_study.mission_server.member.dto;

import com.umc_study.mission_server.member.entity.MemberGender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record SignupRequest(
    @NotBlank
    String name,
    @Email
    String email,
    @NotBlank
    String password,
    @NotBlank
    String nickname,
    @NotNull
    SignupAgreements agreements,
    @NotBlank
    MemberGender sex,
    @NotNull
    LocalDate birthday,
    @NotBlank
    String address1,
    String address2,
    String phoneNumber,
    @NotNull
    NotificationSettingsDto notificationSettings
) {
}
