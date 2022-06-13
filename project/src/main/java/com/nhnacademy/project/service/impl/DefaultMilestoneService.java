package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.domain.dto.MilestoneDto;
import com.nhnacademy.project.repository.ProjectRepository;
import com.nhnacademy.project.service.MilestoneService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultMilestoneService implements MilestoneService {
    private final ProjectRepository projectRepository;


    @Override
    public MilestoneDto getMilestone(Long serialNumber) {
        return projectRepository.findMilestone(serialNumber);
    }

    @Override
    public List<MilestoneDto> getMilestones(Long projectSerialNumber) {
        return projectRepository.findAllMilestones(projectSerialNumber);
    }

    @Override
    public void createMilestone(Long projectSerialNumber, String name) {
        projectRepository.insertMilestone(projectSerialNumber, name);
    }

    @Override
    public void modifyMilestone(Long serialNumber, String name) {
        projectRepository.updateMilestone(serialNumber, name);
    }

    @Override
    public void deleteMilestone(Long serialNumber) {
        projectRepository.deleteMilestone(serialNumber);
    }
}
