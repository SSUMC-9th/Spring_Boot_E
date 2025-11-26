package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    // 200 OK (조회 성공)
    FOUND(HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰 정보를 조회했습니다."),

    // 201 Created (작성 성공)
    WRITE_SUCCESS(HttpStatus.CREATED,
            "REVIEW201_1",
            "성공적으로 리뷰를 작성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}