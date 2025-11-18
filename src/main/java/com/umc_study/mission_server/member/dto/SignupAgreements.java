package com.umc_study.mission_server.member.dto;

public record SignupAgreements(
    boolean tos,
    boolean privacy,
    boolean gps,
    boolean marketing
) {
}
