package com.nhnacademy.projecttaskapi.repository.impl;

import com.nhnacademy.projecttaskapi.domain.dto.TaskDto;
import com.nhnacademy.projecttaskapi.entity.QTask;
import com.nhnacademy.projecttaskapi.entity.Task;
import com.nhnacademy.projecttaskapi.repository.custom.TaskRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

public class TaskRepositoryImpl extends QuerydslRepositorySupport implements TaskRepositoryCustom {
    public TaskRepositoryImpl() {
        super(Task.class);
    }

    @Override
    public Optional<TaskDto> findTask(Long serialNumber) {
        QTask task = QTask.task;

        JPQLQuery query = from(task);
        query.leftJoin(task.milestone);
        query.select(Projections.bean(TaskDto.class,
                task.serialNumber,
                task.milestone.name,
                task.projectMember.pk.memberId,
                task.title,
                task.content
        ));
        query.where(task.serialNumber.eq(serialNumber));

        return Optional.of((TaskDto) query.fetchOne());
    }

    @Override
    public List<TaskDto> findTasks(Long projectSerialNumber) {
        QTask task = QTask.task;

        JPQLQuery query = from(task);
        query.leftJoin(task.milestone);
        query.select(Projections.bean(TaskDto.class,
                task.serialNumber,
                task.milestone.name,
                task.projectMember.pk.memberId,
                task.title,
                task.content
        ));
        query.where(task.projectMember.pk.projectSerialNumber.eq(projectSerialNumber));

        return query.fetch();
    }
}
