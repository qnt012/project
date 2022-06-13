package com.nhnacademy.projecttaskapi.service.impl;

import com.nhnacademy.projecttaskapi.domain.dto.TaskDto;
import com.nhnacademy.projecttaskapi.domain.request.TaskCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.TaskModifyRequest;
import com.nhnacademy.projecttaskapi.entity.*;
import com.nhnacademy.projecttaskapi.exception.MilestoneNotFoundException;
import com.nhnacademy.projecttaskapi.exception.ProjectMemberNotFoundException;
import com.nhnacademy.projecttaskapi.exception.TagNotFoundException;
import com.nhnacademy.projecttaskapi.exception.TaskNotFoundException;
import com.nhnacademy.projecttaskapi.repository.*;
import com.nhnacademy.projecttaskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskTagRepository taskTagRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final MilestoneRepository milestoneRepository;
    private final TagRepository tagRepository;

    @Override
    public List<TaskDto> getTasks(Long projectSerialNumber) {
        return taskRepository.findTasks(projectSerialNumber);
    }

    @Override
    @Transactional
    public Task createTask(TaskCreateRequest request) {
        Milestone milestone = null;
        if (!Objects.isNull(request.getMilestoneSerialNumber())) {
            milestone = milestoneRepository.findById(request.getMilestoneSerialNumber()).orElseThrow(MilestoneNotFoundException::new);
        }

        ProjectMember.Pk projectMemberPk = new ProjectMember.Pk(request.getMemberId(), request.getProjectSerialNumber());
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberPk).orElseThrow(ProjectMemberNotFoundException::new);

        Task task = taskRepository.save(new Task(null, milestone, projectMember, request.getTitle(), request.getContent()));
        saveTaskTagsByTagSerialNumbers(request.getTagSerialNumbers(), task);

        return task;
    }

    @Override
    public TaskDto getTask(Long serialNumber) {
        return taskRepository.findTask(serialNumber).orElseThrow(TaskNotFoundException::new);
    }

    @Override
    @Transactional
    public Task updateTask(TaskModifyRequest request) {
        Task task = taskRepository.findById(request.getTaskSerialNumber()).orElseThrow(TaskNotFoundException::new);

        Milestone milestone = null;
        if (!Objects.isNull(request.getMilestoneSerialNumber())) {
            milestone = milestoneRepository.findById(request.getMilestoneSerialNumber()).orElseThrow(MilestoneNotFoundException::new);
        }
        task.setMilestone(milestone);

        Task result = taskRepository.save(task);

        taskTagRepository.deleteAllByTaskSerialNumber(task.getSerialNumber());
        saveTaskTagsByTagSerialNumbers(request.getTagSerialNumbers(), task);

        return result;
    }

    @Override
    public void deleteTask(Long serialNumber) {
        taskRepository.deleteById(serialNumber);
    }

    @Override
    public void saveTaskTagsByTagSerialNumbers(List<Long> tagSerialNumbers, Task task) {
        if (Objects.isNull(tagSerialNumbers)) {
            tagSerialNumbers = List.of();
        }

        for (Long tagSerialNumber : tagSerialNumbers) {
            Tag tag = tagRepository.findById(tagSerialNumber).orElseThrow(TagNotFoundException::new);
            TaskTag.Pk taskTagPk = new TaskTag.Pk(task.getSerialNumber(), tagSerialNumber);
            taskTagRepository.save(new TaskTag(taskTagPk, task, tag));
        }
    }
}
