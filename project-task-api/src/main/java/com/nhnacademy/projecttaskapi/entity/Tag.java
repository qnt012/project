package com.nhnacademy.projecttaskapi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_serial_number")
    private Long serialNumber;

    @ManyToOne
    @JoinColumn(name = "project_serial_number")
    private Project project;

    @Column(name = "tag_name")
    private String name;
}
