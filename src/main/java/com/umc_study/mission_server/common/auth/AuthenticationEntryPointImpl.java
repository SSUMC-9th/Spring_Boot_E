package com.umc_study.mission_server.common.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc_study.mission_server.common.response.ApiResponse;
import com.umc_study.mission_server.common.response.GeneralErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponse<Void> errorResponse = ApiResponse.error(
            GeneralErrorCode.UNAUTHORIZED,
            null
        );

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
