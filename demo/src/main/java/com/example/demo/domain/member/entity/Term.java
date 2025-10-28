package com.example.demo.domain.member.entity;


import com.example.demo.domain.member.enums.TermDtype;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "term_dtype", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermDtype name;

}
