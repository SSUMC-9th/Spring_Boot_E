package com.umc_study.mission_server.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"success", "code", "message", "data"})
public class ApiResponse<T> {
    @JsonProperty("success")
    private final Boolean success;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("data")
    private T data;

    public static <T> ApiResponse<T> ok(T data) {
        return from(true, GeneralSuccessCode.OK, data);
    }

    public static <T> ApiResponse<T> ok(BaseResponseCode code, T data) {
        return from(true, code, data);
    }

    public static ApiResponse<?> error(BaseResponseCode code) {
        return from(false, code, null);
    }

    public static <T> ApiResponse<T> error(BaseResponseCode code, T data) {
        return from(false, code, data);
    }

    public static <T> ApiResponse<T> from(boolean success, BaseResponseCode code, T data) {
        return new ApiResponse<>(success, code.getCode(), code.getMessage(), data);
    }
}
