package com.nhnacademy.project.controller;

import com.nhnacademy.project.repository.MemberRepository;
import com.nhnacademy.project.service.MemberService;
import com.nhnacademy.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
