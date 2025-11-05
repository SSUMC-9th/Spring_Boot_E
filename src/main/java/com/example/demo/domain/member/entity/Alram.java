package com.example.demo.domain.member.entity;

import com.example.demo.domain.member.enums.AlramDtype;
import com.example.demo.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alram")
@EntityListeners(AuditingEntityListener.class)

public class Alram extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alram_dtype", nullable = false)
    @Enumerated(EnumType.STRING)
    private AlramDtype alramDtype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
