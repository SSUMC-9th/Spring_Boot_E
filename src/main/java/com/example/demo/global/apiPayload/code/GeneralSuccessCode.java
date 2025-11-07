package com.example.demo.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode{

    OK(HttpStatus.OK,
            "COMMON200_1",
            "성공적으로 요청을 처리했습니다."),
    CREATED(HttpStatus.CREATED,
            "COMMON201_1",
            "리소스가 성공적으로 생성되었습니다."),
    ACCEPTED(HttpStatus.ACCEPTED,
            "COMMON202_1",
            "요청이 수락되었습니다."),
    NO_COMMENT(HttpStatus.NO_CONTENT,
            "COMMON204_1",
            "성공했지만 반환할 내용이 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;
    private final String code;
}
