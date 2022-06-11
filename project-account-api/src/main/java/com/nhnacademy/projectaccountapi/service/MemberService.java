package com.nhnacademy.projectaccountapi.service;

import com.nhnacademy.projectaccountapi.domain.request.MemberCreateRequest;
import com.nhnacademy.projectaccountapi.entity.Member;

import java.util.Optional;

public interface MemberService {
    void createMember(MemberCreateRequest request);
    void updateMemberStateActive(String id);
    void updateMemberStateDormant(String id);
    void updateMemberStateWithdraw(String id);
    Optional<Member> getMember(String id);
    Optional<Member> login(String id, String password);
}
