package com.example.demo.domain.member.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MyPageDto {
    private String name;
    private String email;
    private String phoneNumber;
    private int point;
}
