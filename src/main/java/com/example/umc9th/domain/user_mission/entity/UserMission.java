package com.example.umc9th.domain.user_mission.entity;

import com.example.umc9th.domain.common.BaseEntity;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user_mission")
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean complete; // 미션 성공 여부 (true: 성공, false: 진행중)

    // User와 연결 (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Mission과 연결 (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    // 상태 변경 메서드 (나중에 미션 완료 처리할 때 사용)
    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}