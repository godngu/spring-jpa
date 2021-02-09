package com.example.springjpa;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    @Transactional
    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    public Member findById(Long id) {
        Member member = memberJpaRepository.findById(id);
        Member member2 = memberJpaRepository.findById(id);
        return member;
    }
}
