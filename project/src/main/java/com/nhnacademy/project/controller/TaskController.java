package com.nhnacademy.project.controller;

import com.nhnacademy.project.domain.dto.TaskTagDto;
import com.nhnacademy.project.service.MilestoneService;
import com.nhnacademy.project.service.TagService;
import com.nhnacademy.project.service.TaskService;
import java.util.stream.Collectors;
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

    @GetMapping("/{projectSerialNumber}/modify/{taskSerialNumber}")
    public String getTaskModify(@PathVariable Long projectSerialNumber,
                                @PathVariable Long taskSerialNumber,
                                ModelMap modelMap) {
        modelMap.put("projectSerialNumber", projectSerialNumber);
        modelMap.put("task", taskService.getTask(taskSerialNumber));
        modelMap.put("milestones", milestoneService.getMilestones(projectSerialNumber));
        modelMap.put("tags", tagService.getTags(projectSerialNumber));

        List<String> taskTagNames = taskService.getTaskTags(taskSerialNumber)
            .stream()
            .map(TaskTagDto::getTagName)
            .collect(Collectors.toList());

        modelMap.put("taskTagNames", taskTagNames);
        return "project/taskModifyForm";
    }

    @PostMapping("/{projectSerialNumber}/modify/{taskSerialNumber}")
    public String postTaskModify(@PathVariable Long projectSerialNumber,
                                 @PathVariable Long taskSerialNumber,
                                 @RequestParam("title") String title,
                                 @RequestParam("content") String content,
                                 @RequestParam(value = "milestone", required=false) Long milestoneSerialNumber,
                                 @RequestParam(value = "tags", required = false) List<Long> tagSerialNumbers,
                                 Principal principal) {
        taskService.modifyTask(projectSerialNumber, taskSerialNumber, principal.getName(), title, content, milestoneSerialNumber, tagSerialNumbers);
        return "redirect:/tasks/"+projectSerialNumber;
    }

    @GetMapping("/{projectSerialNumber}/view/{taskSerialNumber}")
    public String getTaskView(@PathVariable Long projectSerialNumber,
                              @PathVariable Long taskSerialNumber,
                              ModelMap modelMap) {
        modelMap.put("projectSerialNumber", projectSerialNumber);
        modelMap.put("task", taskService.getTask(taskSerialNumber));
        modelMap.put("taskTags", taskService.getTaskTags(taskSerialNumber));
        return "project/taskView";
    }
}
