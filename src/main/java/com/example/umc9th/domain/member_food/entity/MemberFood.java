// com/example/umc9th/domain/member_food/entity/MemberFood.java
package com.example.umc9th.domain.member_food.entity;

import com.example.umc9th.domain.common.BaseEntity;
import com.example.umc9th.domain.food.entity.Food;
import com.example.umc9th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberFood extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1. Member와의 관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로직
    // MemberFood를 조회할 때, 당장 필요 없는 Member나 Food의 실제 데이터까지 한꺼번에 다 가져오지 않음
    @JoinColumn(name = "member_id") // @ManyToOne과 항상 함께 쓰이는 짝꿍
    // DB에 member_id라는 이름으로 컬럼을 만들어서 FK로 사용하라는 뜻
    @ToString.Exclude
    private Member member;

    // 2. Food와의 관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    @ToString.Exclude
    private Food food;
}