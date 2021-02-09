package com.example.springjpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback(value = false)
    void findById() {

        Member member = new Member("kil", 18);
        memberJpaRepository.save(member);
        em.flush();

        System.out.println("******************************");
        System.out.println("******************************");

        Member findMember = memberJpaRepository.findById(member.getId());
        System.out.println("findMember = " + findMember);
        System.out.println("===================");
        System.out.println(member.hashCode());
        System.out.println(findMember.hashCode());
        System.out.println("===================");

        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
}