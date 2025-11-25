package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.user_mission.entity.UserMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.MissionPreViewDTO toMissionPreViewDTO(UserMission userMission) {
        return MissionResponseDTO.MissionPreViewDTO.builder()
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getName())
                .missionSpec(userMission.getMission().getMissionSpec()) // Mission 엔티티에 필드가 있는지 확인!
                .reward(userMission.getMission().getReward())
                .status(userMission.getComplete() ? "성공" : "진행중")
                .deadline(userMission.getMission().getDeadline())
                .build();
    }

    public static MissionResponseDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<UserMission> userMissionList) {
        List<MissionResponseDTO.MissionPreViewDTO> missionPreViewDTOList = userMissionList.stream()
                .map(MissionConverter::toMissionPreViewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .isLast(userMissionList.isLast())
                .isFirst(userMissionList.isFirst())
                .totalPage(userMissionList.getTotalPages())
                .totalElements(userMissionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}