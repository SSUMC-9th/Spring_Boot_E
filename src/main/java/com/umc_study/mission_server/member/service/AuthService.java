package com.umc_study.mission_server.member.service;

import com.umc_study.mission_server.common.auth.CustomUserDetails;
import com.umc_study.mission_server.common.auth.JwtUtil;
import com.umc_study.mission_server.member.dto.LoginRequest;
import com.umc_study.mission_server.member.dto.LoginResponse;
import com.umc_study.mission_server.member.entity.Member;
import com.umc_study.mission_server.member.exception.MemberErrorCode;
import com.umc_study.mission_server.member.exception.MemberException;
import com.umc_study.mission_server.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public LoginResponse login(@Valid LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email())
            .orElseThrow(() -> new MemberException(MemberErrorCode.INVALID_CREDENTIAL));

        // 비밀번호 검증
        if (!encoder.matches(request.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID_CREDENTIAL);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return LoginResponse.builder()
            .memberId(member.getId())
            .accessToken(accessToken)
            .build();
    }
}
