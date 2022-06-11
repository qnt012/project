package com.nhnacademy.project.repository;

import com.nhnacademy.project.domain.dto.ProjectMemberDto;
import com.nhnacademy.project.domain.dto.TaskDto;
import com.nhnacademy.project.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> findAllByMemberId(String id);
    void insert(String adminId, String name);
    List<ProjectMemberDto> findProjectMembers(Long projectSerialNumber);
    Project findById(Long serialNumber);
    void insertProjectMember(Long serialNumber, String memberId);
    List<TaskDto> findTasks(Long projectSerialNumber);
}
