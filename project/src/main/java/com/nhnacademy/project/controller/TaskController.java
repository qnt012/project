package com.nhnacademy.project.controller;

import com.nhnacademy.project.service.MilestoneService;
import com.nhnacademy.project.service.TagService;
import com.nhnacademy.project.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @PostMapping("/{projectSerialNumber}/create")
    public String postTaskCreate(@PathVariable Long projectSerialNumber,
                                 @RequestParam("title") String title,
                                 @RequestParam("content") String content,
                                 @RequestParam(value = "milestone", required=false) Long milestoneSerialNumber,
                                 @RequestParam(value = "tags", required = false) List<Long> tagSerialNumbers,
                                 Principal principal) {
        taskService.createTask(projectSerialNumber, principal.getName(), title, content, milestoneSerialNumber, tagSerialNumbers);
        return "redirect:/tasks/"+projectSerialNumber;
    }

    @GetMapping("/view/{serialNumber}")
    public String getTaskView(@PathVariable Long serialNumber,
                              ModelMap modelMap) {
        modelMap.put("task", taskService.getTask(serialNumber));
        modelMap.put("taskTags", taskService.getTaskTags(serialNumber));
        return "project/taskView";
    }
}
