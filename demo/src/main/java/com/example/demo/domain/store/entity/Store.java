package com.example.demo.domain.store.entity;

import com.example.demo.domain.member.entity.Region;
import com.example.demo.domain.review.entity.Review;
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
@Table(name = "store")
@EntityListeners(AuditingEntityListener.class)
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Review> reviewList = new ArrayList<>();
}
