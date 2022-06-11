package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.domain.request.member.Token;
import com.nhnacademy.project.domain.request.member.UserResponse;
import com.nhnacademy.project.entity.Member;
import com.nhnacademy.project.repository.MemberRepository;
import com.nhnacademy.project.repository.OauthRepository;
import com.nhnacademy.project.service.OauthLoginService;
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
public class GithubOauthLoginService implements OauthLoginService {
    private final OauthRepository oauthRepository;
    private final MemberRepository memberRepository;

    @Override
    public boolean login(String code) {
        Token accessToken = oauthRepository.getAccessToken("af88cc84ce6da8787c0b", "cb2d5097641dbc2c0384b068cb4f9c864a59ec15", code);
        UserResponse userResponse = oauthRepository.getUser(accessToken.getAccess_token());

        Member member = memberRepository.findByEmail(userResponse.getEmail());

        if (!Objects.isNull(member)){
            putAuthentication(member);
            return true;
        }

        return false;
    }

    @Override
    public void putAuthentication(Member member) {
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
