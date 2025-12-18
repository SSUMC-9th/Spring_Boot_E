package com.example.umc9th.global.jwt.filter;

import com.example.umc9th.global.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService; // 유저 정보 조회용

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. 헤더에서 토큰 추출
        String token = resolveToken(request);

        // 2. 토큰이 유효한지 검사
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 3. 유효하다면 유저 정보(이메일) 추출
            String email = jwtTokenProvider.getEmail(token);

            // 4. DB에서 유저 상세 정보 가져오기
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // 5. 스프링 시큐리티 인증 객체 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // 6. 시큐리티 컨텍스트(임시 저장소)에 저장 -> 이제 전역에서 "이 사람은 로그인 된 사람이야"라고 인식이 됨
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 7. 다음 필터로 넘김
        filterChain.doFilter(request, response);
    }

    // 헤더에서 Bearer 토큰 꺼내기
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}