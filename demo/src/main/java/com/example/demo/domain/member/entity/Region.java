package com.example.demo.domain.member.entity;
import com.example.demo.domain.store.entity.Store;
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
@Table(name = "region")
@EntityListeners(AuditingEntityListener.class)
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "region", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Store> storeList = new ArrayList<>();
}
