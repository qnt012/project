package com.nhnacademy.project.repository;

import com.nhnacademy.project.domain.dto.*;
import com.nhnacademy.project.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> findAllByMemberId(String id);
    void insert(String adminId, String name);
    List<ProjectMemberDto> findProjectMembers(Long projectSerialNumber);
    Project findById(Long serialNumber);
    void insertProjectMember(Long serialNumber, String memberId);
    List<TaskDto> findTasks(Long projectSerialNumber);
    MilestoneDto findMilestone(Long serialNumber);
    List<MilestoneDto> findAllMilestones(Long projectSerialNumber);
    void insertMilestone(Long projectSerialNumber, String name);
    void updateMilestone(Long serialNumber, String name);
    void deleteMilestone(Long serialNumber);
    TagDto findTag(Long serialNumber);
    List<TagDto> findAllTags(Long projectSerialNumber);
    void insertTag(Long projectSerialNumber, String name);
    void updateTag(Long serialNumber, String name);
    void deleteTag(Long serialNumber);
    void insertTask(Long projectSerialNumber, String memberId, String title, String content, Long milestoneSerialNumber, List<Long> tagSerialNumbers);
    void updateTask(Long taskSerialNumber, String title, String content, Long milestoneSerialNumber, List<Long> tagSerialNumbers);
    void deleteTask(Long taskSerialNumber);
    TaskDto findTask(Long serialNumber);
    List<TaskTagDto> findTaskTags(Long serialNumber);
}
