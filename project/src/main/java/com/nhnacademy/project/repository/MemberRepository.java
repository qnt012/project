package com.nhnacademy.project.repository;

import com.nhnacademy.project.domain.request.member.MemberCreateRequest;
import com.nhnacademy.project.entity.Member;


public interface MemberRepository{
    void insert(MemberCreateRequest request);
    Member login(String id, String password);
    Member findById(String id);
}
