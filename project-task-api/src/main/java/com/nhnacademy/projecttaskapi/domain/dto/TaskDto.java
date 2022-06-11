package com.nhnacademy.projecttaskapi.domain.dto;

import com.nhnacademy.projecttaskapi.entity.Milestone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private Long serialNumber;
    private Milestone milestone;
    private String memberId;
    private String title;
    private String content;
}
