package com.example.umc9th.domain.store.entity;

import com.example.umc9th.domain.common.BaseEntity;
import com.example.umc9th.domain.location.entity.Location;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @Column(name = "detail_address", length = 100)
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
}