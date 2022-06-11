package com.nhnacademy.projecttaskapi.repository;

import com.nhnacademy.projecttaskapi.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
