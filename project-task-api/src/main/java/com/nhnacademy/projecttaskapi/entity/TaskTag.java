package com.nhnacademy.projecttaskapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "task_tags")
public class TaskTag {
    @EmbeddedId
    private Pk pk;

    @MapsId(value = "taskSerialNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_serial_number")
    private Task task;

    @MapsId(value = "tagSerialNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_serial_number")
    private Tag tag;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        private Long taskSerialNumber;
        private Long tagSerialNumber;
    }
}
