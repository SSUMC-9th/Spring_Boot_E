package com.umc_study.mission_server.common;

import com.umc_study.mission_server.common.response.ApiResponse;
import com.umc_study.mission_server.common.response.BaseResponseCode;
import com.umc_study.mission_server.common.response.GeneralErrorCode;
import com.umc_study.mission_server.common.response.GeneralException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {
    // 애플리케이션에서 발생하는 커스텀 예외를 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
        GeneralException ex
    ) {

        return ResponseEntity.status(ex.getCode().getStatus())
            .body(ApiResponse.error(
                    ex.getCode(),
                    null
                )
            );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleException(
        MethodArgumentNotValidException ex
    ) {
        // 검사에 실패한 필드와 그에 대한 메시지를 저장하는 Map
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );

        GeneralErrorCode code = GeneralErrorCode.BAD_REQUEST;
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.error(code, errors);

        // 에러 코드, 메시지와 함께 errors를 반환
        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
        Exception ex
    ) {

        BaseResponseCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
            .body(ApiResponse.error(
                    code,
                    ex.getMessage()
                )
            );
    }
}
