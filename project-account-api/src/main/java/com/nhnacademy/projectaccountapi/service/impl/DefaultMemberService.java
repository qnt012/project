package com.nhnacademy.projectaccountapi.service.impl;

import com.nhnacademy.projectaccountapi.domain.request.MemberCreateRequest;
import com.nhnacademy.projectaccountapi.entity.Member;
import com.nhnacademy.projectaccountapi.repository.MemberRepository;
import com.nhnacademy.projectaccountapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void createMember(MemberCreateRequest request) {
        Member member = new Member(request.getId(), request.getPassword(), request.getEmail(), "가입");
        memberRepository.save(member);
    }

    @Override
    public void updateMemberStateActive(String id) {
        memberRepository.updateMemberState(id, "가입");
    }

    @Override
    public void updateMemberStateDormant(String id) {
        memberRepository.updateMemberState(id, "휴면");
    }

    @Override
    public void updateMemberStateWithdraw(String id) {
        memberRepository.updateMemberState(id, "탈퇴");
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> getMember(String id) {
        return memberRepository.findById(id);
    }

    @Override
    public Optional<Member> getMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
