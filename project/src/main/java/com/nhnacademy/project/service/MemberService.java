package com.nhnacademy.project.service;

import com.nhnacademy.project.entity.Member;

public interface MemberService {
    void createMember(String id, String password, String email);

    boolean login(String id, String password);

    void putMemberAuthentication(Member member);

    Member getMember(String id);
}
