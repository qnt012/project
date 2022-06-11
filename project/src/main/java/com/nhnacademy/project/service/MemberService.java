package com.nhnacademy.project.service;

import com.nhnacademy.project.entity.Member;

import java.util.List;

public interface MemberService {
    void createMember(String id, String password, String email);

    Member getMember(String id);

    List<Member> getMembers();
}
