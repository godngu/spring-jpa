package com.example.springjpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.team t where m.id = :memberId")
    Member findByIdWithTeam(@Param("memberId") Long id);

    @Query("select m from Member m join fetch m.team t")
    List<Member> findAllMember();
}
