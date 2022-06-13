package com.nhnacademy.projecttaskapi.controller;

import com.nhnacademy.projecttaskapi.domain.dto.ProjectMemberDto;
import com.nhnacademy.projecttaskapi.domain.request.ProjectCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.ProjectMemberCreateRequest;
import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.entity.ProjectMember;
import com.nhnacademy.projecttaskapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("{serialNumber}")
    public Project getProject(@PathVariable Long serialNumber) {
        return projectService.getProject(serialNumber);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Project postCreate(@RequestBody ProjectCreateRequest request) {
        return projectService.createProject(request);
    }

    @GetMapping("/list/{memberId}")
    public List<Project> getProjects(@PathVariable String memberId) {
        return projectService.getProjects(memberId);
    }


    @GetMapping("{serialNumber}/members")
    public List<ProjectMemberDto> getProjectMembers(@PathVariable Long serialNumber) {
        return projectService.getProjectMembers(serialNumber);
    }

    @PostMapping("members/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectMember postMembersInsert(@RequestBody ProjectMemberCreateRequest request) {
        return projectService.createProjectMember(request);
    }
}
