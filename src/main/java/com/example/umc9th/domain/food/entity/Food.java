// com/example/umc9th/domain/food/entity/Food.java
package com.example.umc9th.domain.food.entity;

import com.example.umc9th.domain.common.BaseEntity;
import com.example.umc9th.domain.food.enums.FoodName;
import com.example.umc9th.domain.member_food.entity.MemberFood;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FoodName foodName;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();
}