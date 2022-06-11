package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.domain.dto.ProjectMemberDto;
import com.nhnacademy.project.entity.Member;
import com.nhnacademy.project.entity.Project;
import com.nhnacademy.project.repository.impl.MemberAdaptor;
import com.nhnacademy.project.repository.impl.ProjectAdaptor;
import com.nhnacademy.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultProjectService implements ProjectService {
    private final ProjectAdaptor projectAdaptor;
    private final MemberAdaptor memberAdaptor;

    @Override
    public List<Project> getProjects(String id) {
        return projectAdaptor.findAllByMemberId(id);
    }

    @Override
    public void createProject(String adminId, String name) {
        projectAdaptor.insert(adminId, name);
    }

    @Override
    public List<Member> getProjectMembers(Long projectSerialNumber) {
        List<ProjectMemberDto> projectMemberDtos = projectAdaptor.findProjectMembers(projectSerialNumber);

        List<Member> projectMembers = new ArrayList<>();
        for (ProjectMemberDto projectMember : projectMemberDtos) {
            projectMembers.add(memberAdaptor.findById(projectMember.getPkMemberId()));
        }
        return projectMembers;
    }

    @Override
    public Project getProject(Long serialNumber) {
        return projectAdaptor.findById(serialNumber);
    }

    @Override
    public void addProjectMember(Long serialNumber, String memberId) {
        projectAdaptor.insertProjectMember(serialNumber, memberId);
    }
}
