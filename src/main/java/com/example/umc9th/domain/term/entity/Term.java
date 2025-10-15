// com/example/umc9th/domain/term/entity/Term.java
package com.example.umc9th.domain.term.entity;

import com.example.umc9th.domain.common.BaseEntity;
import com.example.umc9th.domain.term.enums.TermName;
import com.example.umc9th.domain.member_term.entity.MemberTerm;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Term extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TermName termName;

    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL)
    private List<MemberTerm> memberTermList = new ArrayList<>();
}
