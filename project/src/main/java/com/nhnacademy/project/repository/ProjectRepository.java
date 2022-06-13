package com.nhnacademy.project.repository;

import com.nhnacademy.project.domain.dto.*;
import com.nhnacademy.project.domain.request.project.*;
import com.nhnacademy.project.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> findAllByMemberId(String id);
    void insert(ProjectCreateRequest requestBody);
    List<ProjectMemberDto> findProjectMembers(Long projectSerialNumber);
    Project findById(Long serialNumber);
    void insertProjectMember(ProjectMemberCreateRequest requestBody);
    List<TaskDto> findTasks(Long projectSerialNumber);
    MilestoneDto findMilestone(Long serialNumber);
    List<MilestoneDto> findAllMilestones(Long projectSerialNumber);
    void insertMilestone(MilestoneCreateRequest requestBody);
    void updateMilestone(MilestoneModifyRequest requestBody);
    void deleteMilestone(Long serialNumber);
    TagDto findTag(Long serialNumber);
    List<TagDto> findAllTags(Long projectSerialNumber);
    void insertTag(TagCreateRequest requestBody);
    void updateTag(TagModifyRequest requestBody);
    void deleteTag(Long serialNumber);
    void insertTask(TaskCreateRequest requestBody);
    void updateTask(TaskModifyRequest requestBody);
    void deleteTask(Long taskSerialNumber);
    TaskDto findTask(Long serialNumber);
    List<TaskTagDto> findTaskTags(Long serialNumber);
    void insertComment(CommentCreateRequest requestBody);
    List<CommentDto> findAllComments(Long taskSerialNumber);
    CommentDto findComment(Long serialNumber);
    void updateComment(CommentModifyRequest requestBody);
    void deleteComment(Long serialNumber);
}
