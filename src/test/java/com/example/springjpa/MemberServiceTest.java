package com.example.springjpa;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void findById() {

        Member member = new Member("kil", 18);
        memberService.save(member);

        Member findMember = memberService.findById(member.getId());
        System.out.println("===================");
        System.out.println(member.hashCode());
        System.out.println(findMember.hashCode());
        System.out.println("===================");

        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
}