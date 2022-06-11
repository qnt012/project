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
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_serial_number")
    private Long serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_serial_number")
    private Project project;

    @Column(name = "tag_name")
    private String name;
}
