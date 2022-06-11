package com.nhnacademy.project.repository;

import com.nhnacademy.project.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> findAll(String id);
}
