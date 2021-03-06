package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.domain.dto.TaskDto;
import com.nhnacademy.project.domain.dto.TaskTagDto;
import com.nhnacademy.project.domain.request.project.TaskCreateRequest;
import com.nhnacademy.project.domain.request.project.TaskModifyRequest;
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

    @Override
    public void createTask(Long projectSerialNumber, String memberId, String title, String content, Long milestoneSerialNumber, List<Long> tagSerialNumbers) {
        TaskCreateRequest requestBody = new TaskCreateRequest(projectSerialNumber, memberId, title, content, milestoneSerialNumber, tagSerialNumbers);
        projectRepository.insertTask(requestBody);
    }

    @Override
    public TaskDto getTask(Long serialNumber) {
        return projectRepository.findTask(serialNumber);
    }

    @Override
    public List<TaskTagDto> getTaskTags(Long serialNumber) {
        return projectRepository.findTaskTags(serialNumber);
    }

    @Override
    public void modifyTask(Long taskSerialNumber,
                           String title, String content, Long milestoneSerialNumber,
                           List<Long> tagSerialNumbers) {
        TaskModifyRequest requestBody = new TaskModifyRequest(taskSerialNumber, title, content, milestoneSerialNumber, tagSerialNumbers);
        projectRepository.updateTask(requestBody);
    }

    @Override
    public void deleteTask(Long taskSerialNumber) {
        projectRepository.deleteTask(taskSerialNumber);
    }
}
