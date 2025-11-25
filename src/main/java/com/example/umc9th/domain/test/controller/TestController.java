package com.example.umc9th.domain.test.controller;

import com.example.umc9th.domain.test.converter.TestConverter;
import com.example.umc9th.domain.test.dto.res.TestResDTO;
import com.example.umc9th.domain.test.service.query.TestQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    private final TestQueryService testQueryService;

    // 1. test() 메서드 수정
    @GetMapping("/test")
    public ApiResponse<TestResDTO.Testing> test() {
        return ApiResponse.onSuccess(TestConverter.toTestingDTO("This is Test!"));
    }

    // 2. exception() 메서드 수정
    @GetMapping("/exception")
    public ApiResponse<TestResDTO.Exception> exception(@RequestParam Long flag) {
        testQueryService.checkFlag(flag);
        return ApiResponse.onSuccess(TestConverter.toExceptionDTO("This is Test!"));
    }
}
