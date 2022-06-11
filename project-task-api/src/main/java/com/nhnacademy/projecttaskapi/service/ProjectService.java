package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.request.ProjectCreateRequest;
import com.nhnacademy.projecttaskapi.entity.Project;

public interface ProjectService {
    Project createProject(ProjectCreateRequest request);
}
