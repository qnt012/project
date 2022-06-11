package com.nhnacademy.project.service;

import com.nhnacademy.project.domain.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> getTasks(Long projectSerialNumber);
}
