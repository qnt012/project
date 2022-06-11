package com.nhnacademy.project.repository;

import com.nhnacademy.project.domain.request.member.MemberCreateRequest;
import com.nhnacademy.project.entity.Member;

import java.util.List;


public interface MemberRepository{
    void insert(MemberCreateRequest request);
    Member findById(String id);

    List<Member> findAll();
}
