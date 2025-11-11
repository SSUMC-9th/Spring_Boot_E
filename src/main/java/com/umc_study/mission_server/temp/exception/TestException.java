package com.umc_study.mission_server.temp.exception;

import com.umc_study.mission_server.common.response.GeneralException;

public class TestException extends GeneralException {
    public TestException() {
        super(TestErrorCode.TEST_EXCEPTION);
    }
}
