package com.example.umc9th.domain.member_mission.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResDTO {

    // 개별 미션 정보
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionPreviewDTO {
        private Long memberMissionId;
        private String storeName;       // 가게 이름
        private String missionCondition; // 미션 내용
        private Integer point;          // 포인트
        private Boolean isComplete;     // 진행 상태 (true:완료, false:진행 중)
        private LocalDateTime createdAt; // 미션 도전 시작일
    }

    // 페이징 정보 포함된 전체 응답
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionListDTO {
        List<MemberMissionPreviewDTO> memberMissionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    // 미션 도전 성공 응답 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeMissionDTO {
        private Long memberMissionId;
        private Long memberId;
        private Long missionId;
        private LocalDateTime challengedAt; // 도전 시작일
    }
}