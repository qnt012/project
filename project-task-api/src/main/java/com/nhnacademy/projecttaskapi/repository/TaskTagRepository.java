package com.nhnacademy.projecttaskapi.repository;

import com.nhnacademy.projecttaskapi.domain.dto.TaskTagDto;
import com.nhnacademy.projecttaskapi.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.Pk> {
    List<TaskTagDto> findAllByPkTaskSerialNumber(Long taskSerialNumber);
    void deleteAllByTaskSerialNumber(Long taskSerialNumber);
}
