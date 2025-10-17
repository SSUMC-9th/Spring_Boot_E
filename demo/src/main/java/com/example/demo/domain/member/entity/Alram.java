package com.example.demo.domain.member.entity;

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
@Table(name = "alram")
@EntityListeners(AuditingEntityListener.class)

public class Alram extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_contained", nullable = false)
    private boolean isContained;

    @OneToMany(mappedBy = "alram", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Marketing> marketingList = new ArrayList<>();

    @OneToMany(mappedBy = "alram", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Notice> noticeList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
