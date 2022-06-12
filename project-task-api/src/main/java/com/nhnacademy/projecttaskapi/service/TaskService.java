package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.TaskDto;
import com.nhnacademy.projecttaskapi.domain.request.TaskCreateRequest;
import com.nhnacademy.projecttaskapi.entity.Task;

import java.util.List;

public interface TaskService {
    List<TaskDto> getTasks(Long projectSerialNumber);

    Task createTask(TaskCreateRequest request);

    TaskDto getTask(Long serialNumber);
}
