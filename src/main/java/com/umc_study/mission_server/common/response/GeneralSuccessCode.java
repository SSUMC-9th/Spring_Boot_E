package com.umc_study.mission_server.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseResponseCode {
    OK(HttpStatus.OK, "COMMON_200", null),
    CREATED(HttpStatus.CREATED, "COMMON_201", null),
    NO_CONTENT(HttpStatus.NO_CONTENT, "COMMON_204", null);

    private final HttpStatus status;
    private final String code;
    private final String message;
}
