package com.nhnacademy.projecttaskapi.service.impl;

import com.nhnacademy.projecttaskapi.domain.request.ProjectCreateRequest;
import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.repository.ProjectRepository;
import com.nhnacademy.projecttaskapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultProjectService implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public Project createProject(ProjectCreateRequest request) {
        Project project = new Project(null, request.getAdminSerialNumber(), request.getName(), "활성");
        return projectRepository.save(project);
    }
}
