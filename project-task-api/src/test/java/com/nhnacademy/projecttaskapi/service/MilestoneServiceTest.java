package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.MilestoneDto;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Milestone;
import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.repository.MilestoneRepository;
import com.nhnacademy.projecttaskapi.repository.ProjectRepository;
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
class MilestoneServiceTest {

    @Autowired
    private MilestoneService milestoneService;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private MilestoneRepository milestoneRepository;

    private Project project;
    private Milestone milestone;

    @BeforeEach
    void setUp() {
        project = new Project(1L, "memberId", "project", "활성");
        milestone = new Milestone(1L, project, "마일스톤");
    }

    @Test
    void createMilestone() {
        MilestoneCreateRequest request = new MilestoneCreateRequest(1L, "마일스톤");

        Milestone beforeSave = new Milestone(null, project, request.getName());
        when(projectRepository.findById(request.getProjectSerialNumber())).thenReturn(Optional.of(project));
        when(milestoneRepository.save(beforeSave)).thenReturn(milestone);

        Milestone result = milestoneService.createMilestone(request);
        assertThat(result).isEqualTo(milestone);

        verify(projectRepository).findById(request.getProjectSerialNumber());
        verify(milestoneRepository).save(beforeSave);
    }

    @Test
    void modifyMilestone() {
        Long serialNumber = 1L;
        MilestoneModifyRequest request = new MilestoneModifyRequest("마일스톤수정");
        Milestone modifiedMilestone = new Milestone(1L, project, request.getName());

        when(milestoneRepository.findById(serialNumber)).thenReturn(Optional.of(milestone));
        when(milestoneRepository.save(modifiedMilestone)).thenReturn(modifiedMilestone);

        Milestone result = milestoneService.modifyMilestone(serialNumber, request);
        assertThat(result).isEqualTo(modifiedMilestone);

        verify(milestoneRepository).findById(serialNumber);
        verify(milestoneRepository).save(modifiedMilestone);
    }

    @Test
    void removeMilestone() {
        Long serialNumber = 1L;
        milestoneService.removeMilestone(serialNumber);

        verify(milestoneRepository).deleteById(serialNumber);
    }

    @Test
    void getMilestones() {
        Long projectSerialNumber = 1L;
        MilestoneDto milestoneDto = new MilestoneDto() {
            @Override
            public Long getSerialNumber() {
                return 1L;
            }

            @Override
            public String getName() {
                return "마일스톤";
            }
        };

        when(milestoneRepository.findAllByProjectSerialNumber(projectSerialNumber)).thenReturn(List.of(milestoneDto));

        List<MilestoneDto> result = milestoneService.getMilestones(projectSerialNumber);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getSerialNumber()).isEqualTo(milestoneDto.getSerialNumber());
        assertThat(result.get(0).getName()).isEqualTo(milestoneDto.getName());

        verify(milestoneRepository).findAllByProjectSerialNumber(projectSerialNumber);
        
    }
}