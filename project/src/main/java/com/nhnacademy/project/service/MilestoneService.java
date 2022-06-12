package com.nhnacademy.project.service;

import com.nhnacademy.project.entity.Milestone;
import java.util.List;

public interface MilestoneService {
    Milestone getMilestone(Long serialNumber);
    List<Milestone> getMilestones(Long projectSerialNumber);
    void createMilestone(Long projectSerialNumber, String name);

    void modifyMilestone(Long serialNumber, String name);

    void deleteMilestone(Long serialNumber);
}
