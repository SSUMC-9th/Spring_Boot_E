package com.umc_study.mission_server.temp.converter;

import com.umc_study.mission_server.temp.dto.TestResponse;

public class TestConverter {
    public static TestResponse.Testing toTestingResponse(String testing) {
       return TestResponse.Testing.builder()
           .testString(testing)
           .build();
    }
}
