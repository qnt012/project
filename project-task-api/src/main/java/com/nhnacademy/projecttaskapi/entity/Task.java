package com.nhnacademy.projecttaskapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"taskTags", "comments"})
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

    @OneToMany(mappedBy = "task", cascade = {CascadeType.REMOVE})
    private List<TaskTag> taskTags;

    @OneToMany(mappedBy = "task", cascade = {CascadeType.REMOVE})
    private List<Comment> comments;

    public Task(Long serialNumber, Milestone milestone, ProjectMember projectMember, String title, String content) {
        this.serialNumber = serialNumber;
        this.milestone = milestone;
        this.projectMember = projectMember;
        this.title = title;
        this.content = content;
    }
}
