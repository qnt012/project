package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.ProjectMemberDto;
import com.nhnacademy.projecttaskapi.domain.request.ProjectCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.ProjectMemberCreateRequest;
import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.entity.ProjectMember;
import com.nhnacademy.projecttaskapi.repository.ProjectMemberRepository;
import com.nhnacademy.projecttaskapi.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private ProjectMemberRepository projectMemberRepository;

    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project(1L, "memberId", "project", "활성");
    }

    @Test
    void createProject() {
        ProjectCreateRequest request = new ProjectCreateRequest("adminId", "name");

        Project beforeSave = new Project(null, request.getAdminId(), request.getName(), "활성");

        when(projectRepository.save(beforeSave)).thenReturn(project);

        Project result = projectService.createProject(request);
        assertThat(result).isEqualTo(project);
        verify(projectRepository).save(beforeSave);
    }

    @Test
    void getProjects() {
        String memberId = "member";

        when(projectRepository.findAllByMember(memberId)).thenReturn(List.of(project));
        List<Project> result = projectService.getProjects(memberId);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(project);

        verify(projectRepository).findAllByMember(memberId);
    }

    @Test
    void getProjectMembers() {
        Long projectSerialNumber = 1L;
        ProjectMemberDto projectMember = () -> "member";

        when(projectMemberRepository.findAllByPkProjectSerialNumber(projectSerialNumber)).thenReturn(List.of(projectMember));
        List<ProjectMemberDto> result = projectService.getProjectMembers(projectSerialNumber);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getPkMemberId()).isEqualTo(projectMember.getPkMemberId());

        verify(projectMemberRepository).findAllByPkProjectSerialNumber(projectSerialNumber);
    }

    @Test
    void getProject() {
        Long serialNumber = 1L;

        when(projectRepository.findById(serialNumber)).thenReturn(Optional.of(project));
        Project result = projectService.getProject(serialNumber);

        assertThat(result).isEqualTo(project);

        verify(projectRepository).findById(serialNumber);
    }

    @Test
    void createProjectMember() {
        ProjectMemberCreateRequest request = new ProjectMemberCreateRequest(1L, "member");
        ProjectMember.Pk pk = new ProjectMember.Pk(request.getMemberId(), request.getProjectSerialNumber());
        ProjectMember projectMember = new ProjectMember(pk, project);

        when(projectRepository.findById(request.getProjectSerialNumber())).thenReturn(Optional.of(project));
        when(projectMemberRepository.save(projectMember)).thenReturn(projectMember);

        ProjectMember result = projectService.createProjectMember(request);

        assertThat(result).isEqualTo(projectMember);

        verify(projectRepository).findById(request.getProjectSerialNumber());
        verify(projectMemberRepository).save(projectMember);
    }
}