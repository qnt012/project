package com.nhnacademy.project.repository.impl;

import com.nhnacademy.project.domain.request.project.ProjectCreateRequest;
import com.nhnacademy.project.entity.Project;
import com.nhnacademy.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectAdaptor implements ProjectRepository {
    private final RestTemplate restTemplate;

    @Override
    public List<Project> findAll(String id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Project>> exchange = restTemplate.exchange(
                "http://localhost:7070/projects/"+id,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    @Override
    public void insert(String adminId, String name) {
        ProjectCreateRequest body = new ProjectCreateRequest(adminId, name);

        RequestEntity<ProjectCreateRequest> request = RequestEntity
                .post("http://localhost:7070/projects/insert")
                .accept(MediaType.APPLICATION_JSON)
                .body(body);

        restTemplate.exchange(
                request,
                Void.class
        );
    }
}
