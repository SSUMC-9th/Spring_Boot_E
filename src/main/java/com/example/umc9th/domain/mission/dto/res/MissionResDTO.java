package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreviewDTO {
        private Long missionId;

        // 첫 번째 정의에서 가져옴
        private String storeName;       // 미션이 걸려있는 가게 이름

        // 두 번째 정의에서 가져옴 (필드명을 명확하게 정리)
        private String missionSpec;   // 미션 내용 (이름을 missionSpec으로 통일)
        private String conditional;   // 미션 조건 (예: 리뷰 3회)
        private Integer point;        // 제공 포인트

        // 첫 번째 정의에서 가져옴
        private LocalDateTime createdAt;
    }

    // 2. 페이징 정보를 포함한 전체 목록 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDTO {
        List<MissionPreviewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}