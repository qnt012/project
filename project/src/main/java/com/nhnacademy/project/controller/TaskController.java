package com.nhnacademy.project.controller;

import com.nhnacademy.project.service.MilestoneService;
import com.nhnacademy.project.service.TagService;
import com.nhnacademy.project.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TaskController {
    private final TaskService taskService;
    private final MilestoneService milestoneService;
    private final TagService tagService;

    @GetMapping("/{projectSerialNumber}")
    public String getTasks(@PathVariable Long projectSerialNumber,
                           ModelMap modelMap) {
        modelMap.put("tasks", taskService.getTasks(projectSerialNumber));
        return "project/taskList";
    }

    @GetMapping("/{projectSerialNumber}/create")
    public String getTaskCreate(@PathVariable Long projectSerialNumber,
                                ModelMap modelMap) {
        modelMap.put("projectSerialNumber", projectSerialNumber);
        modelMap.put("milestones", milestoneService.getMilestones(projectSerialNumber));
        modelMap.put("tags", tagService.getTags(projectSerialNumber));
        return "project/taskCreateForm";
    }
}
