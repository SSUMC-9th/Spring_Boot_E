package com.umc_study.mission_server.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseResponseCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON_400", "Bad Request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON_401", "Unauthorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON_403", "Forbidden"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON_404", "Not Found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_500", "Internal Server Error");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
