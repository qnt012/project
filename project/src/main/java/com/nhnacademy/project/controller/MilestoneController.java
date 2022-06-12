package com.nhnacademy.project.controller;

import com.nhnacademy.project.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("milestones")
public class MilestoneController {
    private final MilestoneService milestoneService;

    @GetMapping("{projectSerialNumber}")
    public String getMilestones(@PathVariable Long projectSerialNumber,
                                ModelMap modelMap) {
        modelMap.put("projectSerialNumber", projectSerialNumber);
        modelMap.put("milestones", milestoneService.getMilestones(projectSerialNumber));
        return "project/milestoneList";
    }
}
