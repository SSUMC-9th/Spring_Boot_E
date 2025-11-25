package com.umc_study.mission_server.member.exception;

import com.umc_study.mission_server.common.response.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseResponseCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_0404", "사용자를 찾지 못했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
