package com.nhnacademy.project.service;

import com.nhnacademy.project.entity.Member;

public interface OauthLoginService {
    boolean login(String code);
    void putAuthentication(Member member);
}
