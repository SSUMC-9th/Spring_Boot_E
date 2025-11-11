package com.umc_study.mission_server.temp.dto;

import lombok.Builder;
import lombok.Getter;

public class TestResponse {
    @Builder
    @Getter
    public static class Testing {
        private String testString;
    }
}
