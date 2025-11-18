package com.umc_study.mission_server.store.exception;

import com.umc_study.mission_server.common.response.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseResponseCode {
    FOOD_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_4040", "음식 종류를 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_4041", "가게를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
