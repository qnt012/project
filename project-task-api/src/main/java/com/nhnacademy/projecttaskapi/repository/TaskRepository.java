package com.nhnacademy.projecttaskapi.repository;

import com.nhnacademy.projecttaskapi.entity.Task;
import com.nhnacademy.projecttaskapi.repository.custom.TaskRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom {
}
