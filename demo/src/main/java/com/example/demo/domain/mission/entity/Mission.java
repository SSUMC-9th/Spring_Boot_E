package com.example.demo.domain.mission.entity;


import com.example.demo.domain.mission.entity.mapping.MemberMission;
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
@Table(name = "mission")
@EntityListeners(AuditingEntityListener.class)
public class Mission extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mission_point", nullable = false)
    private Integer missionPoint;

    @Column(name = "mission_name", nullable = false)
    private String missionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<MemberMission> MemberMissionList = new ArrayList<>();
}
