package com.nhnacademy.projecttaskapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_members")
public class ProjectMember {
    @EmbeddedId
    private Pk pk;

    @MapsId(value = "projectSerialNumber")
    @ManyToOne
    @JoinColumn(name = "project_serial_number")
    private Project project;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "member_id")
        private String memberId;
        private Long projectSerialNumber;
    }
}
