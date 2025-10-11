package com.example.demo.domain.mission.entity;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.mapping.MemberFood;
import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.member.enums.SocialType;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.store.entity.Store;
import com.example.demo.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
@EntityListeners(AuditingEntityListener.class)
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mission_count", nullable = false)
    private Integer missionCount;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "mission_name", nullable = false)
    private String missionName;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Review> reviewList = new ArrayList<>();
}
