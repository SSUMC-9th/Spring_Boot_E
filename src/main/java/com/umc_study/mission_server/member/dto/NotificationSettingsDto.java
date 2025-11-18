package com.umc_study.mission_server.member.dto;

public record NotificationSettingsDto(
    boolean newEvent,
    boolean reviewReply,
    boolean askReply
) {
}
