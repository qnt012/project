package com.nhnacademy.project.repository;

import com.nhnacademy.project.domain.dto.ProjectMemberDto;
import com.nhnacademy.project.domain.dto.TaskDto;
import com.nhnacademy.project.entity.Milestone;
import com.nhnacademy.project.entity.Project;
import com.nhnacademy.project.entity.Tag;

import java.util.List;

public interface ProjectRepository {
    List<Project> findAllByMemberId(String id);
    void insert(String adminId, String name);
    List<ProjectMemberDto> findProjectMembers(Long projectSerialNumber);
    Project findById(Long serialNumber);
    void insertProjectMember(Long serialNumber, String memberId);
    List<TaskDto> findTasks(Long projectSerialNumber);
    Milestone findMilestone(Long serialNumber);
    List<Milestone> findAllMilestones(Long projectSerialNumber);
    void insertMilestone(Long projectSerialNumber, String name);
    void updateMilestone(Long serialNumber, String name);
    void deleteMilestone(Long serialNumber);
    Tag findTag(Long serialNumber);
    List<Tag> findAllTags(Long projectSerialNumber);
    void insertTag(Long projectSerialNumber, String name);
    void updateTag(Long serialNumber, String name);
    void deleteTag(Long serialNumber);
}
