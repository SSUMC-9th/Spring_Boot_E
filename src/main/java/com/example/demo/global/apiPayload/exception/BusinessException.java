package com.example.demo.global.apiPayload.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//프로젝트 exception
public class BusinessException extends RuntimeException {
    private BaseErrorCode code;
}
