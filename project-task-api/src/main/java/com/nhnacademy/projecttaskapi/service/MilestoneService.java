package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.request.MilestoneCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Milestone;
import java.util.List;

public interface MilestoneService {
    Milestone createMilestone(MilestoneCreateRequest request);
    Milestone modifyMilestone(MilestoneModifyRequest request);
    void deleteMilestone(Long serialNumber);

    List<Milestone> getMilestones(Long projectSerialNumber);

    Milestone getMilestone(Long serialNumber);
}
