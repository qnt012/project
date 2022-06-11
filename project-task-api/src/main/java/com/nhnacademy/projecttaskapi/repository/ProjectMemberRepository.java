package com.nhnacademy.projecttaskapi.repository;

import com.nhnacademy.projecttaskapi.domain.dto.ProjectMemberDto;
import com.nhnacademy.projecttaskapi.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk> {
    List<ProjectMemberDto> findAllByPkProjectSerialNumber(Long projectSerialNumber);
}
