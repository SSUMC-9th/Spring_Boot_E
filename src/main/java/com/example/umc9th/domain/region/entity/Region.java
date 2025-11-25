// com/example/umc9th/domain/region/entity/Region.java
package com.example.umc9th.domain.region.entity;

import com.example.umc9th.domain.common.BaseEntity;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String regionName;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Store> storeList = new ArrayList<>();
}