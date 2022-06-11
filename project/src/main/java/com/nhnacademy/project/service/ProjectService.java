package com.nhnacademy.project.service;

import com.nhnacademy.project.entity.Member;
import com.nhnacademy.project.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getProjects(String id);
    void createProject(String adminId, String name);
    List<Member> getProjectMembers(Long projectSerialNumber);
    Project getProject(Long serialNumber);
}
