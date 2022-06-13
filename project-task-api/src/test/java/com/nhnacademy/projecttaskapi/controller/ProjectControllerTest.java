package com.nhnacademy.projecttaskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.projecttaskapi.domain.dto.ProjectMemberDto;
import com.nhnacademy.projecttaskapi.domain.request.ProjectCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.ProjectMemberCreateRequest;
import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.entity.ProjectMember;
import com.nhnacademy.projecttaskapi.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getProject() throws Exception {
        Long serialNumber = 1L;
        Project project = new Project(serialNumber, "member", "project", "활성");
        when(projectService.getProject(serialNumber)).thenReturn(project);
        mockMvc.perform(get("/projects/{serialNumber}", serialNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(serialNumber))
                .andExpect(jsonPath("$.adminId").value(project.getAdminId()))
                .andExpect(jsonPath("$.name").value(project.getName()))
                .andExpect(jsonPath("$.status").value(project.getStatus()))
                .andDo(print());
        verify(projectService).getProject(serialNumber);
    }

    @Test
    void postCreate() throws Exception {
        ProjectCreateRequest request = new ProjectCreateRequest("member", "project");
        Project project = new Project(1L, request.getAdminId(), request.getName(), "활성");
        when(projectService.createProject(request)).thenReturn(project);
        mockMvc.perform(post("/projects/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(project.getSerialNumber()))
                .andExpect(jsonPath("$.adminId").value(project.getAdminId()))
                .andExpect(jsonPath("$.name").value(project.getName()))
                .andExpect(jsonPath("$.status").value(project.getStatus()))
                .andDo(print());
        verify(projectService).createProject(request);
    }

    @Test
    void getProjects() throws Exception {
        String memberId = "member";
        Project project = new Project(1L, memberId, "project", "활성");
        List<Project> projects = List.of(project);
        when(projectService.getProjects(memberId)).thenReturn(projects);
        mockMvc.perform(get("/projects/list/{memberId}", memberId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andDo(print());
        verify(projectService).getProjects(memberId);
    }

    @Test
    void getProjectMembers() throws Exception {
        Long serialNumber = 1L;
        ProjectMemberDto memberDto = () -> "member";
        List<ProjectMemberDto> projectMemberDtos = List.of(memberDto);
        when(projectService.getProjectMembers(serialNumber)).thenReturn(projectMemberDtos);
        mockMvc.perform(get("/projects/{serialNumber}/members", serialNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andDo(print());
        verify(projectService).getProjectMembers(serialNumber);
    }

    @Test
    void postMembersInsert() throws Exception{
        ProjectMemberCreateRequest request = new ProjectMemberCreateRequest(1L, "member");
        ProjectMember.Pk pk = new ProjectMember.Pk(request.getMemberId(), request.getProjectSerialNumber());
        Project project = new Project(request.getProjectSerialNumber(), "admin", "project", "활성");
        ProjectMember member = new ProjectMember(pk, project);
        when(projectService.createProjectMember(request)).thenReturn(member);
        mockMvc.perform(post("/projects/members/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.pk").value(pk))
                .andExpect(jsonPath("$.project").value(project))
                .andDo(print());
        verify(projectService).createProjectMember(request);
    }
}