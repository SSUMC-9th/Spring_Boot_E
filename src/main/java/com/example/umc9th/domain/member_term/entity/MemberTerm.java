// com/example/umc9th/domain/member_term/entity/MemberTerm.java
package com.example.umc9th.domain.member_term.entity;

import com.example.umc9th.domain.common.BaseEntity;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.term.entity.Term;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberTerm extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id")
    @ToString.Exclude
    private Term term;
}
