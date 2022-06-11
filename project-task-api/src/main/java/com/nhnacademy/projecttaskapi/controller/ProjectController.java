package com.nhnacademy.projecttaskapi.controller;

import com.nhnacademy.projecttaskapi.domain.dto.ProjectMemberDto;
import com.nhnacademy.projecttaskapi.domain.request.ProjectCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.ProjectMemberCreateRequest;
import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.entity.ProjectMember;
import com.nhnacademy.projecttaskapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("{serialNumber}")
    public ResponseEntity<Project> getProject(@PathVariable Long serialNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(projectService.getProject(serialNumber));
    }

    @PostMapping("/insert")
    public ResponseEntity<Project> postCreate(@RequestBody ProjectCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(projectService.createProject(request));
    }

    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<Project>> getProjects(@PathVariable String memberId) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(projectService.getProjects(memberId));
    }


    @GetMapping("{serialNumber}/members")
    public ResponseEntity<List<ProjectMemberDto>> getProjectMembers(@PathVariable Long serialNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(projectService.getProjectMembers(serialNumber));
    }

    @PostMapping("members/insert")
    public ResponseEntity<ProjectMember> postMembersInsert(@RequestBody ProjectMemberCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(projectService.createProjectMember(request));
    }
}
