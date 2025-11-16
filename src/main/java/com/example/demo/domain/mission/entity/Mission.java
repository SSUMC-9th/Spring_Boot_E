package com.example.demo.domain.mission.entity;


import com.example.demo.domain.mission.entity.mapping.MemberMission;
import com.example.demo.domain.mission.enums.MissionStatus;
import com.example.demo.domain.store.entity.Store;
import com.example.demo.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "mission")
@EntityListeners(AuditingEntityListener.class)
public class Mission extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mission_point", nullable = false)
    private Integer missionPoint;

    @Column(name = "mission_name", nullable = false)
    private String missionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "mission_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    // 상태 변경 메서드
    public void challengeMission() {
        if (this.status == status.COMPLETED) {
            throw new IllegalStateException("이미 완료된 미션입니다.");
        }
        if (this.status == status.IN_PROGRESS) {
            throw new IllegalStateException("이미 도전 중인 미션입니다.");
        }
        this.status = status.IN_PROGRESS;
    }

    public void completeMission() {
        if (this.status != status.IN_PROGRESS) {
            throw new IllegalStateException("도전 중인 미션만 완료할 수 있습니다.");
        }
        this.status = status.COMPLETED;
    }

    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<MemberMission> MemberMissionList = new ArrayList<>();
}
