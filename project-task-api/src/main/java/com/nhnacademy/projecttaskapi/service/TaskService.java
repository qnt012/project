package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> getTasks(Long projectSerialNumber);
}
