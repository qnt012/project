package com.nhnacademy.projecttaskapi.controller;

import com.nhnacademy.projecttaskapi.domain.dto.MilestoneDto;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Milestone;
import com.nhnacademy.projecttaskapi.exception.ValidationFailedException;
import com.nhnacademy.projecttaskapi.service.MilestoneService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("projects/{projectSerialNumber}/milestones")
public class MilestoneController {
    private final MilestoneService milestoneService;

    @GetMapping("/{serialNumber}")
    public MilestoneDto getMilestone(@PathVariable Long serialNumber) {
        return milestoneService.getMilestone(serialNumber);
    }

    @GetMapping
    public List<MilestoneDto> getMilestones(@PathVariable Long projectSerialNumber) {
        return milestoneService.getMilestones(projectSerialNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Milestone postMilestoneCreate(@Valid @RequestBody MilestoneCreateRequest request,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return milestoneService.createMilestone(request);
    }

    @PutMapping("/{serialNumber}")
    public Milestone putMilestone(@Valid @RequestBody MilestoneModifyRequest request,
                                  BindingResult bindingResult,
                                  @PathVariable Long serialNumber) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return milestoneService.modifyMilestone(serialNumber, request);
    }

    @DeleteMapping("/{serialNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMilestone(@PathVariable Long serialNumber) {
        milestoneService.removeMilestone(serialNumber);
    }
}
