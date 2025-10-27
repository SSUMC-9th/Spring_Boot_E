package com.example.demo.domain.member.entity;


import com.example.demo.domain.member.entity.mapping.MemberFood;
import com.example.demo.domain.member.enums.FoodName;
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
@Table(name = "food")
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_name")
    @Enumerated(EnumType.STRING)
    private FoodName name;

    @OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
