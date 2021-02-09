package com.example.springjpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    void saveMember() {
        // given
        Team team = new Team();
        team.setName("LG");
        teamRepository.save(team);

        Member member = new Member();
        member.setUsername("kil");
        member.setTeam(team);
        memberRepository.save(member);

        // when
        Member findMember = memberRepository.findById(member.getId()).get();

        // then
        assertThat(member.getId()).isEqualTo(findMember.getId());
        assertThat(member.getUsername()).isEqualTo(findMember.getUsername());

        System.out.println("$$$$$$$$$$$$$$$$$$$");
        System.out.println(team.getMembers());
        System.out.println(teamRepository.findById(team.getId()).get().getMembers());
        System.out.println("$$$$$$$$$$$$$$$$$$$");
    }

    @Test
    void lazy() {
        // given
        Team team = new Team();
        team.setName("LG");
        teamRepository.save(team);

        Member member = new Member();
        member.setUsername("kil");
        member.setTeam(team);
        memberRepository.save(member);

        em.flush();
        em.clear();


        // when
        Member findMember = memberRepository.findByIdWithTeam(member.getId());

        // then
    }

    @Test
    void nPlusOne() {
        Team team1 = new Team();
        team1.setName("LG");
        teamRepository.save(team1);

        Team team2 = new Team();
        team2.setName("LOTTE");
        teamRepository.save(team2);

        Member member1 = new Member();
        member1.setUsername("kil");
        member1.setTeam(team1);
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setUsername("kiwan");
        member2.setTeam(team2);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        List<Member> members = memberRepository.findAllMember();
        System.out.println("*********");
        System.out.println("N = " + members.size());
        for (Member member : members) {
            System.out.println(member.getTeam().getName());
        }
        System.out.println("*********");


    }
}