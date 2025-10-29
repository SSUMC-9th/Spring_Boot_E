package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // JpaRepository<Member, Long>를 상속받으면
    // findById(memberId) 같은 기본 쿼리는 이미 들어왔다는데? 이게 뭔 말이지
}