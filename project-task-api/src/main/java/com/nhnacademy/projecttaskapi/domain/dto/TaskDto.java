package com.nhnacademy.projecttaskapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private Long serialNumber;
    private String milestoneName;
    private String memberId;
    private String title;
    private String content;
}
