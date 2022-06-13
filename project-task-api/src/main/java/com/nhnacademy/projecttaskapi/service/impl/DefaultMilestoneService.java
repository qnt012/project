package com.nhnacademy.projecttaskapi.service.impl;

import com.nhnacademy.projecttaskapi.domain.dto.MilestoneDto;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Milestone;
import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.exception.MilestoneNotFound;
import com.nhnacademy.projecttaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.projecttaskapi.repository.MilestoneRepository;
import com.nhnacademy.projecttaskapi.repository.ProjectRepository;
import com.nhnacademy.projecttaskapi.service.MilestoneService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultMilestoneService implements MilestoneService {
    private final ProjectRepository projectRepository;
    private final MilestoneRepository milestoneRepository;

    @Override
    public Milestone createMilestone(MilestoneCreateRequest request) {
        Project project = projectRepository.findById(request.getProjectSerialNumber()).orElseThrow(ProjectNotFoundException::new);
        Milestone milestone = new Milestone(null, project, request.getName());
        return milestoneRepository.save(milestone);
    }

    @Override
    @Transactional
    public Milestone modifyMilestone(MilestoneModifyRequest request) {
        Milestone milestone = milestoneRepository.findById(request.getSerialNumber()).orElseThrow(MilestoneNotFound::new);
        milestone.setName(request.getName());
        return milestoneRepository.save(milestone);
    }

    @Override
    public void removeMilestone(Long serialNumber) {
        milestoneRepository.deleteById(serialNumber);
    }

    @Override
    public List<MilestoneDto> getMilestones(Long projectSerialNumber) {
        return milestoneRepository.findAllByProjectSerialNumber(projectSerialNumber);
    }

    @Override
    public MilestoneDto getMilestone(Long serialNumber) {
        return milestoneRepository.findBySerialNumber(serialNumber).orElseThrow(MilestoneNotFound::new);
    }
}
