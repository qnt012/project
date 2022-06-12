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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_serial_number")
    private Long serialNumber;

    @ManyToOne
    @JoinColumn(name = "project_serial_number")
    private Project project;

    @Column(name = "milestone_name")
    private String name;
}
