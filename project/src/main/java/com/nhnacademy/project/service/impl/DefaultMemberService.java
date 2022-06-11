package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.domain.request.member.MemberCreateRequest;
import com.nhnacademy.project.entity.Member;
import com.nhnacademy.project.repository.MemberRepository;
import com.nhnacademy.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createMember(String id, String password, String email) {
        MemberCreateRequest request = new MemberCreateRequest(id, passwordEncoder.encode(password), email);
        memberRepository.insert(request);
    }


    @Override
    public Member getMember(String id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }
}
