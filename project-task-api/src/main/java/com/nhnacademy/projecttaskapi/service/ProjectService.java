package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.ProjectMemberDto;
import com.nhnacademy.projecttaskapi.domain.request.ProjectCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.ProjectMemberCreateRequest;
import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.entity.ProjectMember;

import java.util.List;

public interface ProjectService {
    Project createProject(ProjectCreateRequest request);

    List<Project> getProjects(String memberId);

    List<ProjectMemberDto> getProjectMembers(Long projectSerialNumber);

    Project getProject(Long serialNumber);

    ProjectMember createProjectMember(ProjectMemberCreateRequest request);
}
