package com.nhnacademy.project.repository.impl;

import com.nhnacademy.project.domain.request.member.MemberCreateRequest;
import com.nhnacademy.project.domain.request.member.MemberLoginRequest;
import com.nhnacademy.project.entity.Member;
import com.nhnacademy.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
@RequiredArgsConstructor
public class MemberAdaptor implements MemberRepository {
    private final RestTemplate restTemplate;

    @Override
    public void insert(MemberCreateRequest request) {
        RequestEntity<MemberCreateRequest> requestEntity = RequestEntity
            .post("http://localhost:9090/members/insert")
            .accept(MediaType.APPLICATION_JSON)
            .body(request);

        restTemplate.exchange(
            requestEntity,
            Void.class
        );
    }

    @Override
    public Member login(String id, String password) {
        MemberLoginRequest body = new MemberLoginRequest(id, password);
        RequestEntity<MemberLoginRequest> request = RequestEntity
            .post("http://localhost:9090/members/login")
            .accept(MediaType.APPLICATION_JSON)
            .body(body);

        return restTemplate.exchange(
            request,
            Member.class
        ).getBody();
    }

    @Override
    public Member findById(String id) {
        return restTemplate.exchange(
                "http://localhost:9090/members/"+id,
                HttpMethod.GET,
                null,
                Member.class
        ).getBody();
    }
}
