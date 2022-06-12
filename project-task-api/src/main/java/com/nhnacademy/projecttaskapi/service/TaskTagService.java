package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.TaskTagDto;

import java.util.List;

public interface TaskTagService {
    List<TaskTagDto> getTaskTags(Long serialNumber);
}
