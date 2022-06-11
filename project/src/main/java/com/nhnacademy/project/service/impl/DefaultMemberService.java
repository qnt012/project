package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.domain.request.member.MemberCreateRequest;
import com.nhnacademy.project.entity.Member;
import com.nhnacademy.project.repository.MemberRepository;
import com.nhnacademy.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void createMember(String id, String password, String email) {
        MemberCreateRequest request = new MemberCreateRequest(id, password, email);
        memberRepository.insert(request);
    }

    @Override
    public boolean login(String id, String password) {
        Member member = memberRepository.login(id,  password);
        if (!Objects.isNull(member)) {
            putMemberAuthentication(member);
            return true;
        }
        return false;
    }

    @Override
    public void putMemberAuthentication(Member member) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_MEMBER");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);
        UserDetails userDetails = new User(member.getId(), member.getPassword(),
                Collections.singletonList(simpleGrantedAuthority));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "USER_PASSWORD", authorities);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
    }
}
