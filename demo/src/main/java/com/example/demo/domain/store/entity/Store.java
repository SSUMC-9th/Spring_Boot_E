package com.example.demo.domain.store.entity;

import com.example.demo.domain.mission.entity.Mission;
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
@Table(name = "store")
@EntityListeners(AuditingEntityListener.class)
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "store_address", nullable = false)
    private String storeAddress;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Mission> missionList = new ArrayList<>();
}
