package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.TaskDto;
import com.nhnacademy.projecttaskapi.domain.request.TaskCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.TaskModifyRequest;
import com.nhnacademy.projecttaskapi.entity.ProjectMember;
import com.nhnacademy.projecttaskapi.entity.Tag;
import com.nhnacademy.projecttaskapi.entity.Task;
import com.nhnacademy.projecttaskapi.entity.TaskTag;
import com.nhnacademy.projecttaskapi.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private TaskTagRepository taskTagRepository;

    @MockBean
    private ProjectMemberRepository projectMemberRepository;

    @MockBean
    private MilestoneRepository milestoneRepository;

    @MockBean
    private TagRepository tagRepository;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task(1L, null, null, "title", "content");
    }

    @Test
    void getTasks() {
        Long projectSerialNumber = 1L;
        TaskDto taskDto = new TaskDto();
        taskDto.setSerialNumber(2L);
        taskDto.setMilestoneName("마일스톤");
        taskDto.setMemberId("member");
        taskDto.setTitle("title2");
        taskDto.setContent("content2");

        when(taskRepository.findTasks(projectSerialNumber)).thenReturn(List.of(taskDto));

        List<TaskDto> result = taskService.getTasks(projectSerialNumber);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(taskDto);

        verify(taskRepository).findTasks(projectSerialNumber);
    }

    @Test
    void createTask() {
        TaskCreateRequest request = new TaskCreateRequest(1L, "member", "title", "content", null, null);

        ProjectMember.Pk projectMemberPk = new ProjectMember.Pk(request.getMemberId(), request.getProjectSerialNumber());
        ProjectMember projectMember = new ProjectMember(projectMemberPk, null);

        when(projectMemberRepository.findById(projectMemberPk)).thenReturn(Optional.of(projectMember));
        task.setProjectMember(projectMember);

        Task beforeSave = new Task(null, null, projectMember, request.getTitle(), request.getContent());
        when(taskRepository.save(beforeSave)).thenReturn(task);

        Task result = taskService.createTask(request);

        assertThat(result).isEqualTo(task);

        verify(projectMemberRepository).findById(projectMemberPk);
        verify(taskRepository).save(beforeSave);
    }

    @Test
    void getTask() {
        Long serialNumber = 2L;

        TaskDto taskDto = new TaskDto();
        taskDto.setSerialNumber(serialNumber);
        taskDto.setMilestoneName("마일스톤");
        taskDto.setMemberId("member");
        taskDto.setTitle("title2");
        taskDto.setContent("content2");

        when(taskRepository.findTask(serialNumber)).thenReturn(Optional.of(taskDto));

        assertThat(taskService.getTask(serialNumber)).isEqualTo(taskDto);

        verify(taskRepository).findTask(serialNumber);

    }

    @Test
    void updateTask() {
        TaskModifyRequest request = new TaskModifyRequest(1L, "title2", "content2", null, null);

        when(taskRepository.findById(request.getTaskSerialNumber())).thenReturn(Optional.of(task));

        Task updated = new Task(task.getSerialNumber(), null, task.getProjectMember(), request.getTitle(), request.getContent());

        when(taskRepository.save(task)).thenReturn(updated);

        Task result = taskService.updateTask(request);
        assertThat(result).isEqualTo(updated);

        verify(taskRepository).findById(request.getTaskSerialNumber());
        verify(taskRepository).save(task);
    }

    @Test
    void deleteTask() {
        Long serialNumber = 1L;

        taskService.deleteTask(serialNumber);

        verify(taskRepository).deleteById(serialNumber);
    }

    @Test
    void saveTaskTagsByTagSerialNumbers() {
        List<Long> tagSerialNumbers = List.of(1L);
        Tag tag = new Tag(1L, null, "태그");

        when(tagRepository.findById(tagSerialNumbers.get(0))).thenReturn(Optional.of(tag));

        TaskTag.Pk taskTagPk = new TaskTag.Pk(task.getSerialNumber(), tag.getSerialNumber());
        TaskTag taskTag = new TaskTag(taskTagPk, task, tag);

        taskService.saveTaskTagsByTagSerialNumbers(tagSerialNumbers, task);

        verify(tagRepository).findById(tagSerialNumbers.get(0));
        verify(taskTagRepository).save(taskTag);
    }
}