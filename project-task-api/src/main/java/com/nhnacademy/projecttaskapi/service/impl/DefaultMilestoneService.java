package com.nhnacademy.projecttaskapi.service.impl;

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
    public Milestone modifyMilestone(MilestoneModifyRequest request) {
        Milestone milestone = milestoneRepository.findById(request.getSerialNumber()).orElseThrow(MilestoneNotFound::new);
        milestone.setName(request.getName());
        return milestoneRepository.save(milestone);
    }

    @Override
    public void deleteMilestone(Long serialNumber) {
        milestoneRepository.deleteById(serialNumber);
    }

    @Override
    public List<Milestone> getMilestones(Long projectSerialNumber) {
        return milestoneRepository.findAllByProjectSerialNumber(projectSerialNumber);
    }
}
