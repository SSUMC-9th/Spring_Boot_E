package com.umc_study.mission_server.review.exception;

import com.umc_study.mission_server.common.response.BaseResponseCode;
import com.umc_study.mission_server.common.response.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseResponseCode code) {
        super(code);
    }
}
