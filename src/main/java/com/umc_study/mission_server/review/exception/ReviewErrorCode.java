package com.umc_study.mission_server.review.exception;

import com.umc_study.mission_server.common.response.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseResponseCode {
    BAD_SEARCH_ORDER_MODE(HttpStatus.BAD_REQUEST, "REVIEW_4000", "잘못된 정렬 기준입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
