package com.nhnacademy.projectaccountapi.service.impl;

import com.nhnacademy.projectaccountapi.domain.request.MemberCreateRequest;
import com.nhnacademy.projectaccountapi.entity.Member;
import com.nhnacademy.projectaccountapi.repository.MemberRepository;
import com.nhnacademy.projectaccountapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createMember(MemberCreateRequest request) {
        Member member = new Member(null, request.getId(), passwordEncoder.encode(request.getPassword()), request.getEmail(), "가입");
        memberRepository.save(member);
    }

    @Override
    public void updateMemberStateActive(Long serialNumber) {
        memberRepository.updateMemberState(serialNumber, "가입");
    }

    @Override
    public void updateMemberStateDormant(Long serialNumber) {
        memberRepository.updateMemberState(serialNumber, "휴면");
    }

    @Override
    public void updateMemberStateWithdraw(Long serialNumber) {
        memberRepository.updateMemberState(serialNumber, "탈퇴");
    }

    @Override
    public Optional<Member> getMember(Long serialNumber) {
        return memberRepository.findById(serialNumber);
    }

    @Override
    public Optional<Member> login(String id, String password) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent() && passwordEncoder.matches(password, member.get().getPassword())) {
            return member;
        }
        return Optional.empty();
    }
}
