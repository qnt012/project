package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.entity.Milestone;
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
    public List<Milestone> getMilestones(Long projectSerialNumber) {
        return projectRepository.findAllMilestones(projectSerialNumber);
    }
}
