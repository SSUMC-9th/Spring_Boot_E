package com.example.umc9th.domain.location.entity;

import com.example.umc9th.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Location extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 지역 이름 (예: 서울, 강남구, 홍대 등)

    // 💡 나중에 Store(가게) 엔티티를 만들 때,
    // Store 안에서 'private Location location;' 으로 이 엔티티를 연결해서 쓰게 됩니다.
}