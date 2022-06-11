package com.nhnacademy.project.repository;

import com.nhnacademy.project.domain.request.MemberCreateRequest;
import com.nhnacademy.project.entity.Member;


public interface MemberRepository{
    void insert(MemberCreateRequest request);
    Member login(String id, String password);
}
