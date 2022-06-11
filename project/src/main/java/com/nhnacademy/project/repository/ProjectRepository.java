package com.nhnacademy.project.repository;

import com.nhnacademy.project.domain.dto.ProjectMemberDto;
import com.nhnacademy.project.entity.Member;
import com.nhnacademy.project.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> findAll(String id);
    void insert(String adminId, String name);
    List<ProjectMemberDto> findProjectMembers(Long projectSerialNumber);
}
