package com.nhnacademy.projecttaskapi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_serial_number")
    private Long serialNumber;

    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "project_name")
    private String name;

    @Column(name = "project_status")
    private String status;
}
