package com.umc_study.mission_server.member.dto;

import com.umc_study.mission_server.member.entity.Member;
import com.umc_study.mission_server.member.entity.MemberGender;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record MemberResponse(
    Long id,
    String name,
    MemberGender sex,
    LocalDate birthday,
    String address1,
    String address2,
    LocalDateTime registeredAt,
    String nickname,
    String email,
    String phoneNumber,
    Long currentPoint
) {
    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
            .id(member.getId())
            .name(member.getName())
            .sex(member.getSex())
            .birthday(member.getBirthday())
            .address1(member.getAddress1())
            .address2(member.getAddress2())
            .registeredAt(member.getRegisteredAt())
            .nickname(member.getNickname())
            .email(member.getEmail())
            .phoneNumber(member.getPhoneNumber())
            .currentPoint(member.getCurrentPoint())
            .build();
    }
}
