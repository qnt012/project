package com.nhnacademy.projecttaskapi.repository;

import com.nhnacademy.projecttaskapi.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk> {
}
