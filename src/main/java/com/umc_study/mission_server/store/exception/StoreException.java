package com.umc_study.mission_server.store.exception;

import com.umc_study.mission_server.common.response.BaseResponseCode;
import com.umc_study.mission_server.common.response.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseResponseCode code) {
        super(code);
    }
}
