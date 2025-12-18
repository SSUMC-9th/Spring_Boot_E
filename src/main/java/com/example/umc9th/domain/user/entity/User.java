package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.common.BaseEntity;
import com.example.umc9th.domain.user.enums.Role;
import jakarta.persistence.*;
import lombok.*; // 👈 롬복 전체 import

@Entity
@Getter // 👈 ⭐ 이 줄이 반드시 있어야 합니다! (이게 없어서 빨간 줄이 뜬 거예요)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    // ... 기존 필드들 ...

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void encodePassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}