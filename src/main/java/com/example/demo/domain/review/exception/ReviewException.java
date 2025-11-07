package com.example.demo.domain.review.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import com.example.demo.global.apiPayload.exception.BusinessException;

public class ReviewException extends BusinessException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
