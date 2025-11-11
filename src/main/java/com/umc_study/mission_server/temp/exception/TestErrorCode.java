package com.umc_study.mission_server.temp.exception;

import com.umc_study.mission_server.common.response.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TestErrorCode implements BaseResponseCode {
    TEST_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST_4001", "이거는 테스트");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
