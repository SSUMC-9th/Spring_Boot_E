package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.common.BaseEntity;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate deadline;

    @Column(length = 200)
    private String conditional;

    private Integer point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}