package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.domain.dto.ProjectMemberDto;
import com.nhnacademy.project.domain.request.project.ProjectCreateRequest;
import com.nhnacademy.project.domain.request.project.ProjectMemberCreateRequest;
import com.nhnacademy.project.entity.Member;
import com.nhnacademy.project.entity.Project;
import com.nhnacademy.project.repository.MemberRepository;
import com.nhnacademy.project.repository.ProjectRepository;
import com.nhnacademy.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultProjectService implements ProjectService {
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Project> getProjects(String id) {
        return projectRepository.findAllByMemberId(id);
    }

    @Override
    public void createProject(String adminId, String name) {
        ProjectCreateRequest requestBody = new ProjectCreateRequest(adminId, name);
        projectRepository.insert(requestBody);
    }

    @Override
    public List<Member> getProjectMembers(Long projectSerialNumber) {
        List<ProjectMemberDto> projectMemberDtos = projectRepository.findProjectMembers(projectSerialNumber);

        List<Member> projectMembers = new ArrayList<>();
        for (ProjectMemberDto projectMember : projectMemberDtos) {
            projectMembers.add(memberRepository.findById(projectMember.getPkMemberId()));
        }
        return projectMembers;
    }

    @Override
    public Project getProject(Long serialNumber) {
        return projectRepository.findById(serialNumber);
    }

    @Override
    public void addProjectMember(Long serialNumber, String memberId) {
        ProjectMemberCreateRequest requestBody = new ProjectMemberCreateRequest(serialNumber, memberId);
        projectRepository.insertProjectMember(requestBody);
    }
}
