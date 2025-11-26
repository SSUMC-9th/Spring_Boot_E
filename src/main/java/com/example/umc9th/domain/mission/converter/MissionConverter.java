package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getStoreName())
                .conditional(mission.getConditinal()) // 오타 수정 권장 (conditional)
                .point(mission.getPoint())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    // Page 객체를 리스트 DTO로 변환
    public static List<MissionResDTO.MissionPreviewDTO> toMissionPreviewListDTO(Page<Mission> missionPage) {
        return missionPage.stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());
    }
    // DTO -> Entity 변환 (AddMissionDTO)
    public static Mission toMission(MissionReqDTO.AddMissionDTO dto, Store store) {
        return Mission.builder()
                .conditinal(dto.getMissionCondition()) // 👈 Mission Entity의 필드명에 맞춰서 사용
                .point(dto.getPoint())
                .store(store) // Service에서 찾은 Store 엔티티 주입
                .build();
    }

    // 2. Page -> MissionListDTO
    public static MissionResDTO.MissionListDTO toMissionListDTO(Page<Mission> missionPage) {

        List<MissionResDTO.MissionPreviewDTO> missionPreviewList = missionPage.stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MissionListDTO.builder()
                .missionList(missionPreviewList)
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionPreviewList.size())
                .build();
    }
}