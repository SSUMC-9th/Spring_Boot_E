package com.umc_study.mission_server.temp.controller;

import com.umc_study.mission_server.common.response.ApiResponse;
import com.umc_study.mission_server.temp.converter.TestConverter;
import com.umc_study.mission_server.temp.dto.TestResponse.Testing;
import com.umc_study.mission_server.temp.service.TestQueryService;
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

    @GetMapping("/test")
    public ApiResponse<Testing> test() {
        return ApiResponse.ok(TestConverter.toTestingResponse("This is test!"));
    }

    @GetMapping("/exception")
    public ApiResponse<Testing> exception(@RequestParam Long flag) {
        testQueryService.checkFlag(flag);
        return ApiResponse.ok(TestConverter.toTestingResponse("test for /exception"));
    }
}
