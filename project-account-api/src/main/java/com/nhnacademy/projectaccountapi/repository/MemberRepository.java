package com.nhnacademy.projectaccountapi.repository;

import com.nhnacademy.projectaccountapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(String id);

    @Transactional
    @Modifying
    @Query("update Member set state = :state where serialNumber = :serialNumber")
    void updateMemberState(@Param("serialNumber") Long serialNumber, @Param("state") String state);
}
