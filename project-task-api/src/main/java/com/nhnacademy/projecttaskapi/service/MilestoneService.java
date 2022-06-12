package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.request.MilestoneCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Milestone;

public interface MilestoneService {
    Milestone createMilestone(MilestoneCreateRequest request);
    Milestone modifyMilestone(MilestoneModifyRequest request);
    void deleteMilestone(Long serialNumber);
}
