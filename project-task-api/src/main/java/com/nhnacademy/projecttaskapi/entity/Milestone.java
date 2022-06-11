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
@Table(name = "milestones")
public class Milestone {
    @Id
    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_serial_number")
    private Project project;

    @Column(name = "milestone_name")
    private String name;
}
