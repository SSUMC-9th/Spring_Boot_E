package com.umc_study.mission_server.member.service;

import com.umc_study.mission_server.member.dto.MemberResponse;
import com.umc_study.mission_server.member.dto.SignupRequest;
import com.umc_study.mission_server.member.entity.Member;
import com.umc_study.mission_server.member.entity.MemberFoodType;
import com.umc_study.mission_server.member.exception.MemberErrorCode;
import com.umc_study.mission_server.member.exception.MemberException;
import com.umc_study.mission_server.member.repository.MemberRepository;
import com.umc_study.mission_server.store.entity.FoodType;
import com.umc_study.mission_server.store.exception.StoreErrorCode;
import com.umc_study.mission_server.store.exception.StoreException;
import com.umc_study.mission_server.store.repository.FoodTypeRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final FoodTypeRepository foodTypeRepository;

    @Transactional
    public MemberResponse signup(SignupRequest request) {
        LocalDateTime now = LocalDateTime.now();
        Member member = Member.builder()
            .providerId("test")
            .providerType("test")
            .agreeTos(request.agreements().tos() ? now : null)
            .agreePrivacy(request.agreements().privacy() ? now : null)
            .agreeGps(request.agreements().gps() ? now : null)
            .agreeMarketing(request.agreements().marketing() ? now : null)
            .name(request.name())
            .sex(request.sex())
            .birthday(request.birthday())
            .address1(request.address1())
            .address2(request.address2())
            .registeredAt(now)
            .deletedAt(null)
            .lastLogin(null)
            .nickname(request.nickname())
            .email(request.email())
            .phoneNumber(request.phoneNumber())
            .verifiedPhoneNumber(false)
            .notificationAskReply(request.notificationSettings().askReply())
            .notificationNewEvent(request.notificationSettings().newEvent())
            .notificationReviewReply(request.notificationSettings().reviewReply())
            .currentPoint(0L)
            .build();
        memberRepository.save(member);
        return MemberResponse.from(member);
    }

    @Transactional
    public void updatePreferFoodTypes(Long memberId, List<Long> foodTypes) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        member.getPreferFoodTypes().clear();
        foodTypes.stream()
            .map(foodTypeId -> foodTypeRepository
                .findById(foodTypeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.FOOD_TYPE_NOT_FOUND)))
            .map(foodType -> MemberFoodType.builder()
                .member(member)
                .foodType(foodType)
                .build())
            .forEach(foodType -> member.getPreferFoodTypes().add(foodType));
    }
}
