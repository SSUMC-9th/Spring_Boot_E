package com.example.demo.domain.review.entity;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.store.entity.Store;
import com.example.demo.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name ="review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "star",  nullable = false)
    private Integer star;

    @Column(name = "review_content", nullable = false)
    private String reviewContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();
}