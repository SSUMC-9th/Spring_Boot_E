package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.common.BaseEntity;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user_mission.entity.UserMission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer reward; // 리워드 (포인트 등)

    private LocalDate deadline; // 마감 기한

    private String missionSpec; // 미션 내용 (예: 10,000원 이상 식사 시)

    // 가게(Store)와 연결 (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // 유저 미션(UserMission)과 양방향 매핑
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();
}