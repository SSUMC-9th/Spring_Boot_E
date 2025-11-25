package com.umc_study.mission_server.mission.exception;

import com.umc_study.mission_server.common.response.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseResponseCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_0404", "미션을 찾지 못했습니다."),
    INVALID_STATE_EXPECT_PENDING(HttpStatus.FORBIDDEN, "MISSION_4030", "대기중인 미션만 처리할 수 있습니다."),
    INVALID_STATE_EXPECT_PROGRESS(HttpStatus.FORBIDDEN, "MISSION_4031", "진행중인 미션만 처리할 수 있습니다.")
    ;
    
    private final HttpStatus status;
    private final String code;
    private final String message;
}
