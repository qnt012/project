package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.TaskDto;
import com.nhnacademy.projecttaskapi.domain.request.TaskCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.TaskModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Task;

import java.util.List;

public interface TaskService {
    List<TaskDto> getTasks(Long projectSerialNumber);

    Task createTask(TaskCreateRequest request);

    TaskDto getTask(Long serialNumber);

    Task updateTask(TaskModifyRequest request);

    void deleteTask(Long serialNumber);
}
