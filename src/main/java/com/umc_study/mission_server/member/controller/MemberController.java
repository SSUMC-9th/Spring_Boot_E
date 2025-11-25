package com.umc_study.mission_server.member.controller;

import com.umc_study.mission_server.common.response.ApiResponse;
import com.umc_study.mission_server.member.dto.MemberResponse;
import com.umc_study.mission_server.member.dto.SignupRequest;
import com.umc_study.mission_server.member.dto.UpdatePreferFoodTypesRequest;
import com.umc_study.mission_server.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResponse> signup(@RequestBody SignupRequest request) {
        return ApiResponse.ok(memberService.signup(request));
    }

    @PutMapping("/prefer-foods")
    public ApiResponse<?> updatePreferFoods(@RequestBody @Valid UpdatePreferFoodTypesRequest request) {
        memberService.updatePreferFoodTypes(request.memberId(), request.foodTypes());
        return ApiResponse.ok(null);
    }
}
