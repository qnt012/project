package com.nhnacademy.project.repository.impl;

import com.nhnacademy.project.domain.request.member.MemberCreateRequest;
import com.nhnacademy.project.entity.Member;
import com.nhnacademy.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;


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
    public Member findById(String id) {
        return restTemplate.exchange(
                "http://localhost:9090/members/"+id,
                HttpMethod.GET,
                null,
                Member.class
        ).getBody();
    }

    @Override
    public List<Member> findAll() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Member>> exchange = restTemplate.exchange(
                "http://localhost:9090/members",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    @Override
    public Member findByEmail(String email) {
        return restTemplate.exchange(
                "http://localhost:9090/members/email/"+email,
                HttpMethod.GET,
                null,
                Member.class
        ).getBody();
    }
}
