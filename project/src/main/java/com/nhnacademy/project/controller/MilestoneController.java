package com.nhnacademy.project.controller;

import com.nhnacademy.project.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("{projectSerialNumber}/create")
    public String getMilestoneCreate(@PathVariable Long projectSerialNumber,
                                     ModelMap modelMap) {
        modelMap.put("projectSerialNumber", projectSerialNumber);
        return "project/milestoneCreateForm";
    }

    @PostMapping("{projectSerialNumber}/create")
    public String postMilestoneCreate(@PathVariable Long projectSerialNumber,
                                      @RequestParam("name") String name) {

        milestoneService.createMilestone(projectSerialNumber, name);
        return "redirect:/milestones/" + projectSerialNumber;
    }

    @GetMapping("{projectSerialNumber}/modify/{serialNumber}")
    public String getMilestoneModify(@PathVariable Long projectSerialNumber,
                                     @PathVariable Long serialNumber,
                                     ModelMap modelMap) {
        modelMap.put("milestone", milestoneService.getMilestone(serialNumber));
        modelMap.put("projectSerialNumber", projectSerialNumber);
        return "project/milestoneModifyForm";
    }

    @PostMapping("{projectSerialNumber}/modify/{serialNumber}")
    public String postMilestoneModify(@PathVariable Long projectSerialNumber,
                                     @PathVariable Long serialNumber,
                                      @RequestParam("name") String name) {
        milestoneService.modifyMilestone(serialNumber, name);
        return "redirect:/milestones/" + projectSerialNumber;
    }

    @GetMapping("{projectSerialNumber}/delete/{serialNumber}")
    public String getMilestoneDelete(@PathVariable Long projectSerialNumber,
                                     @PathVariable Long serialNumber) {
        milestoneService.deleteMilestone(serialNumber);
        return "redirect:/milestones/" + projectSerialNumber;
    }
}
