package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.domain.dto.TaskDto;
import com.nhnacademy.project.repository.ProjectRepository;
import com.nhnacademy.project.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {
    private final ProjectRepository projectRepository;

    @Override
    public List<TaskDto> getTasks(Long projectSerialNumber) {
        return projectRepository.findTasks(projectSerialNumber);
    }
}