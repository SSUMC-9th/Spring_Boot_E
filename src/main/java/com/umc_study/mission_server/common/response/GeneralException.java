package com.umc_study.mission_server.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private final BaseResponseCode code;
}
