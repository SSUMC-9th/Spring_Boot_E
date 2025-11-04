package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.common.BaseEntity;
import com.example.umc9th.domain.user.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Column(length = 40)
    private String address;

    private LocalDate birth;

    @Column(length = 50)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

}