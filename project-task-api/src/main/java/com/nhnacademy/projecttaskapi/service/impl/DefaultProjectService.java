package com.nhnacademy.projecttaskapi.service.impl;

import com.nhnacademy.projecttaskapi.domain.dto.ProjectMemberDto;
import com.nhnacademy.projecttaskapi.domain.request.ProjectCreateRequest;
import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.entity.ProjectMember;
import com.nhnacademy.projecttaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.projecttaskapi.repository.ProjectMemberRepository;
import com.nhnacademy.projecttaskapi.repository.ProjectRepository;
import com.nhnacademy.projecttaskapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultProjectService implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Override
    @Transactional
    public Project createProject(ProjectCreateRequest request) {
        Project project = projectRepository.save(new Project(null, request.getAdminId(), request.getName(), "활성"));
        ProjectMember.Pk pk = new ProjectMember.Pk(request.getAdminId(), project.getSerialNumber());
        projectMemberRepository.save(new ProjectMember(pk, project));
        return project;
    }

    @Override
    public List<Project> getProjects(String memberId) {
        return projectRepository.findAllByMember(memberId);
    }

    @Override
    public List<ProjectMemberDto> getProjectMembers(Long projectSerialNumber) {
        return projectMemberRepository.findAllByPkProjectSerialNumber(projectSerialNumber);
    }

    @Override
    public Project getProject(Long serialNumber) {
        return projectRepository.findById(serialNumber).orElseThrow(ProjectNotFoundException::new);
    }
}
