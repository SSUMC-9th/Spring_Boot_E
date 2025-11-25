package com.umc_study.mission_server.member.exception;

import com.umc_study.mission_server.common.response.BaseResponseCode;
import com.umc_study.mission_server.common.response.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseResponseCode code) {
        super(code);
    }
}
