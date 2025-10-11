package com.example.demo.domain.member.entity;

import com.example.demo.domain.member.entity.mapping.MemberFood;
import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.member.enums.SocialType;
import com.example.demo.domain.review.entity.Reply;
import com.example.demo.domain.review.entity.Review;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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
@Table(name = "point")
@EntityListeners(AuditingEntityListener.class)
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "points", nullable = false)
    private Integer points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
