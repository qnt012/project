package com.nhnacademy.projecttaskapi.repository.impl;

import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.entity.QProject;
import com.nhnacademy.projecttaskapi.entity.QProjectMember;
import com.nhnacademy.projecttaskapi.repository.custom.ProjectRepositoryCustom;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProjectRepositoryImpl extends QuerydslRepositorySupport implements ProjectRepositoryCustom {
    public ProjectRepositoryImpl() {
        super(Project.class);
    }

    @Override
    public List<Project> findAllByMember(String memberId) {
        QProject project = QProject.project;
        QProjectMember projectMember = QProjectMember.projectMember;

        JPQLQuery query = from(project);
        query.innerJoin(projectMember).on(projectMember.pk.projectSerialNumber.eq(project.serialNumber));
        query.select(project);
        query.where(projectMember.pk.memberId.eq(memberId));

        return query.fetch();
    }
}
