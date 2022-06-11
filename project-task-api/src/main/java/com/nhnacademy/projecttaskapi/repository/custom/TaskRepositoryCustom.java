package com.nhnacademy.projecttaskapi.repository.custom;

import com.nhnacademy.projecttaskapi.domain.dto.TaskDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface TaskRepositoryCustom {
    List<TaskDto> findTasks(Long projectSerialNumber);
}
