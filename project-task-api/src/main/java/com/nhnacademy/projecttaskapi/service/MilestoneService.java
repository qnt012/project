package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.MilestoneDto;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Milestone;
import java.util.List;

public interface MilestoneService {
    Milestone createMilestone(MilestoneCreateRequest request);
    Milestone modifyMilestone(Long serialNumber, MilestoneModifyRequest request);
    void removeMilestone(Long serialNumber);

    List<MilestoneDto> getMilestones(Long projectSerialNumber);

    MilestoneDto getMilestone(Long serialNumber);
}
