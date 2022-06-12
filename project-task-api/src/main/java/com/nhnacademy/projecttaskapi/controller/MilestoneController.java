package com.nhnacademy.projecttaskapi.controller;

import com.nhnacademy.projecttaskapi.domain.request.MilestoneCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Milestone;
import com.nhnacademy.projecttaskapi.service.MilestoneService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/milestones")
public class MilestoneController {
    private final MilestoneService milestoneService;

    @GetMapping("/{projectSerialNumber}")
    public ResponseEntity<List<Milestone>> getMilestones(@PathVariable Long projectSerialNumber) {
        return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(milestoneService.getMilestones(projectSerialNumber));
    }

    @PostMapping("/insert")
    public ResponseEntity<Milestone> postMilestoneCreate(@RequestBody MilestoneCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(milestoneService.createMilestone(request));
    }

    @PutMapping("/modify")
    public ResponseEntity<Milestone> putMilestone(@RequestBody MilestoneModifyRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(milestoneService.modifyMilestone(request));
    }

    @DeleteMapping("/delete/{serialNumber}")
    public void deleteMilestone(@PathVariable Long serialNumber) {
        milestoneService.deleteMilestone(serialNumber);
    }
}
