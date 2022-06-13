package com.nhnacademy.projecttaskapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_serial_number")
    private Long serialNumber;

    @ManyToOne
    @JoinColumn(name = "milestone_serial_number")
    private Milestone milestone;

    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "member_id"),
            @JoinColumn(name = "project_serial_number")
    })
    private ProjectMember projectMember;

    @Column(name = "task_title")
    private String title;

    @Column(name = "task_content")
    private String content;
}
