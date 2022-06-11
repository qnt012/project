package com.nhnacademy.project.controller;

import com.nhnacademy.project.entity.Project;
import com.nhnacademy.project.service.ProjectService;
import com.nhnacademy.project.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks/{projectSerialNumber}")
public class TaskController {
    private final ProjectService projectService;
    private final TaskService taskService;

    @ModelAttribute("project")
    public Project getProject(@PathVariable Long projectSerialNumber) {
        return projectService.getProject(projectSerialNumber);
    }

    @GetMapping
    public String getTasks(@PathVariable Long projectSerialNumber,
                           ModelMap modelMap) {
        modelMap.put("tasks", taskService.getTasks(projectSerialNumber));
        return "project/taskList";
    }
}
