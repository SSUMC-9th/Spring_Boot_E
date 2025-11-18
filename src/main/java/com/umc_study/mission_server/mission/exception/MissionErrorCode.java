package com.umc_study.mission_server.mission.exception;

import com.umc_study.mission_server.common.response.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseResponseCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_0404", "미션을 찾지 못했습니다.");
    ;
    
    private final HttpStatus status;
    private final String code;
    private final String message;
}
