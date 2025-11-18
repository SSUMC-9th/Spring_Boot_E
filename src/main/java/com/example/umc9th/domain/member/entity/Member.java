// com/example/umc9th/domain/member/entity/Member.java
package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.common.BaseEntity;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.SocialType;
import com.example.umc9th.domain.member_food.entity.MemberFood;
import com.example.umc9th.domain.member_mission.entity.MemberMission;
import com.example.umc9th.domain.member_term.entity.MemberTerm;
import com.example.umc9th.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 1. 클래스 레벨 어노테이션
@Entity // 이 클래스가 DB 테이블과 매핑된다고 JPA에게 알려줌
@Getter // Lombok 어노테이션으로, getter 매서드 자동 생성
@Builder // Lombok 어노테이션으로, 객체 생성할 때 가독성 좋아지게 도와줌
@NoArgsConstructor(access = AccessLevel.PROTECTED) // new Member()처럼 아무 값도 없는 객체 생성 방지
@AllArgsConstructor
public class Member extends BaseEntity {

    // 2. ID (기본 키) 설정
    @Id // 이게 PK다 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK값은 DB가 알아서 자동으로 생성해달라
    // IDENTITY: 데이터가 추가될 때마다 DB의 AUTO_INCREMENT 기능이 동작해서 ID 값을 1, 2, 3... 순서대로 부여함
    private Long id;

    // 3. 컬럼 메핑
    @Column(nullable = false, length = 20) // 필드에 대한 세부 설정
    private String name;

    @Enumerated(EnumType.STRING) // Gender 같은 Enum 타입을 DB에 저장할 때 쓰는 어노테이션
    // EnumType.STRING으로 설정하면 DB에 Enum의 이름(예: "MALE", "FEMALE")이 문자열로 저장됨
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    // @Column이 없어도 JPA가 알아서 잘 만들어 줌
    private LocalDate birth;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40) // 상세주소 추가
    private String detailAddress;

    // 소셜 로그인 관련 필드는 nullable = true 로 설정
    private String socialUid;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(columnDefinition = "integer default 0") // DB에 직접 명령을 내리는 columnDefinition 옵션
    private Integer point;

    @Column(nullable = false, length = 50)
    private String email;

    // @Column이 없어도 JPA가 알아서 잘 만들어 줌
    private String phoneNumber;

    // 4. 연관관계 매핑
    // Member가 삭제될 때 관련된 데이터들도 함께 삭제되도록 Cascade 설정
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    // 1:N 관계에서 N쪽(FK를 가진 쪽)이 항상 주인이 됨
    private List<MemberFood> memberFoodList = new ArrayList<>();

     @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
     @Builder.Default
     private List<MemberMission> memberMissionList = new ArrayList<>();

     @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
     @Builder.Default
     private List<Review> reviewList = new ArrayList<>();

     @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
     @Builder.Default
     private List<MemberTerm> memberTermList = new ArrayList<>();
}