package com.nhnacademy.project.service;

import com.nhnacademy.project.domain.dto.TaskDto;
import com.nhnacademy.project.domain.dto.TaskTagDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> getTasks(Long projectSerialNumber);

    void createTask(Long projectSerialNumber, String memberId, String title, String content, Long milestoneSerialNumber, List<Long> tagSerialNumbers);

    TaskDto getTask(Long serialNumber);

    List<TaskTagDto> getTaskTags(Long serialNumber);

    void modifyTask(Long taskSerialNumber, String title, String content, Long milestoneSerialNumber, List<Long> tagSerialNumbers);
}
