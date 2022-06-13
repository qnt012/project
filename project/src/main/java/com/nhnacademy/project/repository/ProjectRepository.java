package com.nhnacademy.project.repository;

import com.nhnacademy.project.domain.dto.*;
import com.nhnacademy.project.domain.request.project.*;
import com.nhnacademy.project.entity.Project;

import java.util.List;

public interface ProjectRepository {
    Project findById(Long serialNumber);
    List<Project> findAllByMemberId(String id);
    void insert(ProjectCreateRequest requestBody);

    List<ProjectMemberDto> findProjectMembers(Long projectSerialNumber);
    void insertProjectMember(ProjectMemberCreateRequest requestBody);

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

    TaskDto findTask(Long serialNumber);
    List<TaskDto> findTasks(Long projectSerialNumber);
    void insertTask(TaskCreateRequest requestBody);
    void updateTask(TaskModifyRequest requestBody);
    void deleteTask(Long taskSerialNumber);

    List<TaskTagDto> findTaskTags(Long serialNumber);

    CommentDto findComment(Long serialNumber);
    List<CommentDto> findAllComments(Long taskSerialNumber);
    void insertComment(CommentCreateRequest requestBody);
    void updateComment(CommentModifyRequest requestBody);
    void deleteComment(Long serialNumber);
}
