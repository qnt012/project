package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.entity.Member;
import com.nhnacademy.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username);
        if (Objects.isNull(member)) throw new UsernameNotFoundException(username);

        return new User(member.getId(), member.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER")));
    }
}
