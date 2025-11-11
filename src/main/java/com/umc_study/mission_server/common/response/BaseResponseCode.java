package com.umc_study.mission_server.common.response;

import org.springframework.http.HttpStatus;

public interface BaseResponseCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
