package com.nhnacademy.projecttaskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.projecttaskapi.domain.dto.TaskDto;
import com.nhnacademy.projecttaskapi.domain.dto.TaskTagDto;
import com.nhnacademy.projecttaskapi.domain.request.TaskCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.TaskModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Task;
import com.nhnacademy.projecttaskapi.service.TaskService;
import com.nhnacademy.projecttaskapi.service.TaskTagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private TaskTagService taskTagService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTask() throws Exception {
        Long serialNumber = 1L;
        TaskDto taskDto = new TaskDto();
        taskDto.setSerialNumber(serialNumber);
        taskDto.setMilestoneName("마일스톤");
        taskDto.setMemberId("member");
        taskDto.setTitle("title");
        taskDto.setContent("content");
        when(taskService.getTask(serialNumber)).thenReturn(taskDto);
        mockMvc.perform(get("/tasks/{serialNumber}", serialNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(serialNumber))
                .andExpect(jsonPath("$.milestoneName").value(taskDto.getMilestoneName()))
                .andExpect(jsonPath("$.memberId").value(taskDto.getMemberId()))
                .andExpect(jsonPath("$.title").value(taskDto.getTitle()))
                .andExpect(jsonPath("$.content").value(taskDto.getContent()))
                .andDo(print());
        verify(taskService).getTask(serialNumber);
    }

    @Test
    void getTaskTags() throws Exception {
        Long serialNumber = 1L;
        TaskTagDto taskTagDto = new TaskTagDto() {
            @Override
            public Long getPkTaskSerialNumber() {
                return 1L;
            }

            @Override
            public Long getPkTagSerialNumber() {
                return 1L;
            }

            @Override
            public String getTagName() {
                return "태그";
            }
        };

        List<TaskTagDto> taskTags = List.of(taskTagDto);
        when(taskTagService.getTaskTags(serialNumber)).thenReturn(taskTags);
        mockMvc.perform(get("/tasks/tags/{serialNumber}", serialNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andDo(print());
        verify(taskTagService).getTaskTags(serialNumber);
    }

    @Test
    void getTasks() throws Exception {
        Long projectSerialNumber = 1L;
        TaskDto taskDto = new TaskDto();
        taskDto.setSerialNumber(1L);
        taskDto.setMilestoneName("마일스톤");
        taskDto.setMemberId("member");
        taskDto.setTitle("title");
        taskDto.setContent("content");
        List<TaskDto> taskTags = List.of(taskDto);
        when(taskService.getTasks(projectSerialNumber)).thenReturn(taskTags);
        mockMvc.perform(get("/tasks/list/{projectSerialNumber}", projectSerialNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andDo(print());
        verify(taskService).getTasks(projectSerialNumber);
    }

    @Test
    void postTaskCreate() throws Exception {
        TaskCreateRequest request = new TaskCreateRequest(1L, "member", "title", "content", null, null);
        Task task = new Task(1L, null, null, request.getTitle(), request.getContent());

        when(taskService.createTask(request)).thenReturn(task);
        mockMvc.perform(post("/tasks/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(task.getSerialNumber()))
                .andExpect(jsonPath("$.title").value(task.getTitle()))
                .andExpect(jsonPath("$.content").value(task.getContent()))
                .andDo(print());
        verify(taskService).createTask(request);
    }

    @Test
    void putTask() throws Exception {
        TaskModifyRequest request = new TaskModifyRequest(1L, "title 수정", "content 수정", null, null);
        Task task = new Task(request.getTaskSerialNumber(), null, null, request.getTitle(), request.getContent());

        when(taskService.updateTask(request)).thenReturn(task);
        mockMvc.perform(put("/tasks/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(task.getSerialNumber()))
                .andExpect(jsonPath("$.title").value(task.getTitle()))
                .andExpect(jsonPath("$.content").value(task.getContent()))
                .andDo(print());
        verify(taskService).updateTask(request);
    }

    @Test
    void deleteTask() throws Exception {
        Long serialNumber = 1L;
        mockMvc.perform(delete("/tasks/delete/{serialNumber}", serialNumber))
                .andExpect(status().isOk())
                .andDo(print());
        verify(taskService).deleteTask(serialNumber);
    }
}