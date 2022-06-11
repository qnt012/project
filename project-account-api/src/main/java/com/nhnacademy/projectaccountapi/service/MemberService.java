package com.nhnacademy.projectaccountapi.service;

import com.nhnacademy.projectaccountapi.domain.request.MemberCreateRequest;
import com.nhnacademy.projectaccountapi.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void createMember(MemberCreateRequest request);
    void updateMemberStateActive(String id);
    void updateMemberStateDormant(String id);
    void updateMemberStateWithdraw(String id);
    List<Member> getMembers();
    Optional<Member> getMember(String id);
}
