package com.nhnacademy.projectaccountapi.service;

import com.nhnacademy.projectaccountapi.domain.request.MemberCreateRequest;
import com.nhnacademy.projectaccountapi.entity.Member;

import java.util.Optional;

public interface MemberService {
    void createMember(MemberCreateRequest request);
    void updateMemberStateActive(Long serialNumber);
    void updateMemberStateDormant(Long serialNumber);
    void updateMemberStateWithdraw(Long serialNumber);
    Optional<Member> getMember(Long serialNumber);
    Optional<Member> login(String id, String password);
}
