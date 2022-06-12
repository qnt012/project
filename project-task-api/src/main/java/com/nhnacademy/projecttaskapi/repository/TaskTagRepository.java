package com.nhnacademy.projecttaskapi.repository;

import com.nhnacademy.projecttaskapi.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.Pk> {
}
