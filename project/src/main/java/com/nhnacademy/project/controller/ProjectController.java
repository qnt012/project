package com.nhnacademy.project.controller;

import com.nhnacademy.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public String getProjects(Principal principal,
                              ModelMap modelMap) {
        modelMap.put("projects", projectService.getProjects(principal.getName()));
        return "project/projectList";
    }

    @GetMapping("/create")
    public String getCreate() {
        return "project/projectForm";
    }

    @PostMapping("/create")
    public String postCreate(@RequestParam("name") String name,
                             Principal principal) {
        projectService.createProject(principal.getName(), name);
        return "redirect:/projects";
    }

    @GetMapping("{serialNumber}/members")
    public String getProjectMembers(@PathVariable Long serialNumber,
                                    ModelMap modelMap) {
        modelMap.put("project", projectService.getProject(serialNumber));
        modelMap.put("members", projectService.getProjectMembers(serialNumber));
        return "project/members";
    }
}
