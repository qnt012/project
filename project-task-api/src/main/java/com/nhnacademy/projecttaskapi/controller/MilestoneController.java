package com.nhnacademy.projecttaskapi.controller;

import com.nhnacademy.projecttaskapi.domain.dto.MilestoneDto;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Milestone;
import com.nhnacademy.projecttaskapi.service.MilestoneService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/milestones")
public class MilestoneController {
    private final MilestoneService milestoneService;

    @GetMapping("/{serialNumber}")
    public MilestoneDto getMilestone(@PathVariable Long serialNumber) {
        return milestoneService.getMilestone(serialNumber);
    }

    @GetMapping("/list/{projectSerialNumber}")
    public List<MilestoneDto> getMilestones(@PathVariable Long projectSerialNumber) {
        return milestoneService.getMilestones(projectSerialNumber);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Milestone postMilestoneCreate(@RequestBody MilestoneCreateRequest request) {
        return milestoneService.createMilestone(request);
    }

    @PutMapping("/update")
    public Milestone putMilestone(@RequestBody MilestoneModifyRequest request) {
        return milestoneService.modifyMilestone(request);
    }

    @DeleteMapping("/delete/{serialNumber}")
    public void deleteMilestone(@PathVariable Long serialNumber) {
        milestoneService.removeMilestone(serialNumber);
    }
}
