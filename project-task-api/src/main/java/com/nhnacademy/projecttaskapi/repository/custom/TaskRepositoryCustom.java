package com.nhnacademy.projecttaskapi.repository.custom;

import com.nhnacademy.projecttaskapi.domain.dto.TaskDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface TaskRepositoryCustom {
    Optional<TaskDto> findTask(Long serialNumber);
    List<TaskDto> findTasks(Long projectSerialNumber);
}
