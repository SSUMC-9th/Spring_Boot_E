package com.umc_study.mission_server.mission.exception;

import com.umc_study.mission_server.common.response.BaseResponseCode;
import com.umc_study.mission_server.common.response.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseResponseCode code) {
        super(code);
    }
}
