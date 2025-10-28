package com.umc_study.mission_server.member.repository;

import com.umc_study.mission_server.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT DISTINCT m FROM Member m WHERE m.id = :id")
    Optional<Member> findById(@Param("id") Long id);

    @Query("SELECT DISTINCT m FROM Member m JOIN FETCH m.preferFoodTypes WHERE m.id = :id")
    Optional<Member> findByIdWithPreferFoodTypes(@Param("id") Long id);
}
