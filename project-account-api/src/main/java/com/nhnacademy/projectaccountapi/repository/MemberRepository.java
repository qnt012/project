package com.nhnacademy.projectaccountapi.repository;

import com.nhnacademy.projectaccountapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, String> {

    @Transactional
    @Modifying
    @Query("update Member set state = :state where id = :id")
    void updateMemberState(@Param("id") String id, @Param("state") String state);

    Optional<Member> findByEmail(String email);
}
