package com.nhnacademy.projecttaskapi.controller;

import com.nhnacademy.projecttaskapi.domain.dto.ProjectMemberDto;
import com.nhnacademy.projecttaskapi.domain.request.ProjectCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.ProjectMemberCreateRequest;
import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.entity.ProjectMember;
import com.nhnacademy.projecttaskapi.exception.ValidationFailedException;
import com.nhnacademy.projecttaskapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project postCreate(@Valid @RequestBody ProjectCreateRequest request,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return projectService.createProject(request);
    }

    @GetMapping("/memberId/{memberId}")
    public List<Project> getProjects(@PathVariable String memberId) {
        return projectService.getProjects(memberId);
    }


    @GetMapping("{serialNumber}/members")
    public List<ProjectMemberDto> getProjectMembers(@PathVariable Long serialNumber) {
        return projectService.getProjectMembers(serialNumber);
    }

    @PostMapping("{serialNumber}/members")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectMember postMembersInsert(@PathVariable Long serialNumber, @RequestBody ProjectMemberCreateRequest request) {
        return projectService.createProjectMember(serialNumber, request);
    }
}
