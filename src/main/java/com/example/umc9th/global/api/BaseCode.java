package com.example.umc9th.global.api;

// ApiResponse 안에 있는 ReasonDTO를 임포트
import com.example.umc9th.global.api.ApiResponse.ReasonDTO;

public interface BaseCode {

    // 반환 타입을 ApiResponse.ReasonDTO로 수정
    public ReasonDTO getReason();

    public ReasonDTO getReasonHttpStatus();
}