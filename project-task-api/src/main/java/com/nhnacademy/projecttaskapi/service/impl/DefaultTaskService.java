package com.nhnacademy.projecttaskapi.service.impl;

import com.nhnacademy.projecttaskapi.domain.dto.TaskDto;
import com.nhnacademy.projecttaskapi.repository.TaskRepository;
import com.nhnacademy.projecttaskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> getTasks(Long projectSerialNumber) {
        return taskRepository.findTasks(projectSerialNumber);
    }
}
