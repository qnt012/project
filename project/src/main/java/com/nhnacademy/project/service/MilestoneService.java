package com.nhnacademy.project.service;

import com.nhnacademy.project.domain.dto.MilestoneDto;

import java.util.List;

public interface MilestoneService {
    MilestoneDto getMilestone(Long serialNumber);
    List<MilestoneDto> getMilestones(Long projectSerialNumber);
    void createMilestone(Long projectSerialNumber, String name);

    void modifyMilestone(Long serialNumber, String name);

    void deleteMilestone(Long serialNumber);
}
