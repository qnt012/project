package com.nhnacademy.projectaccountapi.repository;

import com.nhnacademy.projectaccountapi.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("testId", "testPassword", "test@b.c", "가입");
        entityManager.merge(member);
    }

    @Test
    void updateMemberState() {
        member.setState("탈퇴");
        entityManager.merge(member);

        Optional<Member> found = memberRepository.findById(member.getId());
        assertThat(member.getState()).isEqualTo("탈퇴");
        assertThat(found).isEqualTo(Optional.of(member));
    }

    @Test
    void findByEmail() {
        Optional<Member> found = memberRepository.findByEmail(member.getEmail());
        assertThat(found).isEqualTo(Optional.of(member));
    }
}