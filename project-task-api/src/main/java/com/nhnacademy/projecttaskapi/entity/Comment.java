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
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_serial_number")
    private Long serialNumber;

    @ManyToOne
    @JoinColumn(name = "task_serial_number")
    private Task task;

    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "member_id"),
            @JoinColumn(name = "project_serial_number")
    })
    private ProjectMember projectMember;

    @Column(name = "comment_content")
    private String content;
}
