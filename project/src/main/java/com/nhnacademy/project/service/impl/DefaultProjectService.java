package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.entity.Project;
import com.nhnacademy.project.repository.impl.ProjectAdaptor;
import com.nhnacademy.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultProjectService implements ProjectService {
    private final ProjectAdaptor projectAdaptor;


    @Override
    public List<Project> getProjects(String id) {
        return projectAdaptor.findAll(id);
    }
}
