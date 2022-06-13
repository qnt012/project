package com.nhnacademy.project.repository.impl;

import com.nhnacademy.project.domain.dto.*;
import com.nhnacademy.project.domain.request.project.*;
import com.nhnacademy.project.entity.Project;
import com.nhnacademy.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectAdaptor implements ProjectRepository {
    private final RestTemplate restTemplate;

    @Override
    public List<Project> findAllByMemberId(String id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Project>> exchange = restTemplate.exchange(
                "http://localhost:7070/projects/list/"+id,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    @Override
    public void insert(ProjectCreateRequest requestBody) {
        RequestEntity<ProjectCreateRequest> request = RequestEntity
                .post("http://localhost:7070/projects/insert")
                .accept(MediaType.APPLICATION_JSON)
                .body(requestBody);

        restTemplate.exchange(
                request,
                Void.class
        );
    }

    @Override
    public List<ProjectMemberDto> findProjectMembers(Long projectSerialNumber) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<ProjectMemberDto>> exchange = restTemplate.exchange(
                "http://localhost:7070/projects/" + projectSerialNumber + "/members",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    @Override
    public Project findById(Long serialNumber) {
         return restTemplate.exchange(
                "http://localhost:7070/projects/" + serialNumber,
                HttpMethod.GET,
                null,
                Project.class).getBody();
    }

    @Override
    public void insertProjectMember(ProjectMemberCreateRequest requestBody) {
        RequestEntity<ProjectMemberCreateRequest> request = RequestEntity
                .post("http://localhost:7070/projects/members/insert")
                .accept(MediaType.APPLICATION_JSON)
                .body(requestBody);

        restTemplate.exchange(
                request,
                Void.class
        );
    }

    @Override
    public List<TaskDto> findTasks(Long projectSerialNumber) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<TaskDto>> exchange = restTemplate.exchange(
                "http://localhost:7070/tasks/list/" + projectSerialNumber,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    @Override
    public MilestoneDto findMilestone(Long serialNumber) {
        return restTemplate.exchange(
            "http://localhost:7070/milestones/" + serialNumber,
            HttpMethod.GET,
            null,
                MilestoneDto.class).getBody();
    }

    @Override
    public List<MilestoneDto> findAllMilestones(Long projectSerialNumber) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<MilestoneDto>> exchange = restTemplate.exchange(
            "http://localhost:7070/milestones/list/" + projectSerialNumber,
            HttpMethod.GET,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });
        return exchange.getBody();
    }

    @Override
    public void insertMilestone(MilestoneCreateRequest requestBody) {
        RequestEntity<MilestoneCreateRequest> request = RequestEntity
            .post("http://localhost:7070/milestones/insert")
            .accept(MediaType.APPLICATION_JSON)
            .body(requestBody);

        restTemplate.exchange(
            request,
            Void.class
        );
    }

    @Override
    public void updateMilestone(MilestoneModifyRequest requestBody) {
        RequestEntity<MilestoneModifyRequest> request = RequestEntity
            .put("http://localhost:7070/milestones/update")
            .accept(MediaType.APPLICATION_JSON)
            .body(requestBody);

        restTemplate.exchange(
            request,
            Void.class
        );
    }

    @Override
    public void deleteMilestone(Long serialNumber) {
        restTemplate.exchange(
            "http://localhost:7070/milestones/delete/" + serialNumber,
            HttpMethod.DELETE,
            null,
            Void.class);
    }

    @Override
    public TagDto findTag(Long serialNumber) {
        return restTemplate.exchange(
                "http://localhost:7070/tags/" + serialNumber,
                HttpMethod.GET,
                null,
                TagDto.class).getBody();
    }

    @Override
    public List<TagDto> findAllTags(Long projectSerialNumber) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<TagDto>> exchange = restTemplate.exchange(
                "http://localhost:7070/tags/list/" + projectSerialNumber,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    @Override
    public void insertTag(TagCreateRequest requestBody) {
        RequestEntity<TagCreateRequest> request = RequestEntity
                .post("http://localhost:7070/tags/insert")
                .accept(MediaType.APPLICATION_JSON)
                .body(requestBody);

        restTemplate.exchange(
                request,
                Void.class
        );
    }

    @Override
    public void updateTag(TagModifyRequest requestBody) {
        RequestEntity<TagModifyRequest> request = RequestEntity
                .put("http://localhost:7070/tags/update")
                .accept(MediaType.APPLICATION_JSON)
                .body(requestBody);

        restTemplate.exchange(
                request,
                Void.class
        );
    }

    @Override
    public void deleteTag(Long serialNumber) {
        restTemplate.exchange(
                "http://localhost:7070/tags/delete/" + serialNumber,
                HttpMethod.DELETE,
                null,
                Void.class);
    }

    @Override
    public void insertTask(TaskCreateRequest requestBody) {
        RequestEntity<TaskCreateRequest> request = RequestEntity
                .post("http://localhost:7070/tasks/insert")
                .accept(MediaType.APPLICATION_JSON)
                .body(requestBody);

        restTemplate.exchange(
                request,
                Void.class
        );
    }

    @Override
    public void updateTask(TaskModifyRequest requestBody) {
        RequestEntity<TaskModifyRequest> request = RequestEntity
            .put("http://localhost:7070/tasks/update")
            .accept(MediaType.APPLICATION_JSON)
            .body(requestBody);

        restTemplate.exchange(
            request,
            Void.class
        );
    }

    @Override
    public void deleteTask(Long taskSerialNumber) {
        restTemplate.exchange(
                "http://localhost:7070/tasks/delete/" + taskSerialNumber,
                HttpMethod.DELETE,
                null,
                Void.class);
    }

    @Override
    public TaskDto findTask(Long serialNumber) {
        return restTemplate.exchange(
                "http://localhost:7070/tasks/" + serialNumber,
                HttpMethod.GET,
                null,
                TaskDto.class).getBody();
    }

    @Override
    public List<TaskTagDto> findTaskTags(Long serialNumber) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<TaskTagDto>> exchange = restTemplate.exchange(
                "http://localhost:7070/tasks/tags/" + serialNumber,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    @Override
    public void insertComment(CommentCreateRequest requestBody) {
        RequestEntity<CommentCreateRequest> request = RequestEntity
                .post("http://localhost:7070/comments/insert")
                .accept(MediaType.APPLICATION_JSON)
                .body(requestBody);

        restTemplate.exchange(
                request,
                Void.class
        );
    }

    @Override
    public List<CommentDto> findAllComments(Long taskSerialNumber) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<CommentDto>> exchange = restTemplate.exchange(
                "http://localhost:7070/comments/list/" + taskSerialNumber,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    @Override
    public CommentDto findComment(Long serialNumber) {
        return restTemplate.exchange(
                "http://localhost:7070/comments/" + serialNumber,
                HttpMethod.GET,
                null,
                CommentDto.class).getBody();
    }

    @Override
    public void updateComment(CommentModifyRequest requestBody) {
        RequestEntity<CommentModifyRequest> request = RequestEntity
                .put("http://localhost:7070/comments/update")
                .accept(MediaType.APPLICATION_JSON)
                .body(requestBody);

        restTemplate.exchange(
                request,
                Void.class
        );
    }

    @Override
    public void deleteComment(Long serialNumber) {
        restTemplate.exchange(
                "http://localhost:7070/comments/delete/" + serialNumber,
                HttpMethod.DELETE,
                null,
                Void.class);
    }
}
