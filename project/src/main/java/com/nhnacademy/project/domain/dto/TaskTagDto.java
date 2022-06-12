package com.nhnacademy.project.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskTagDto {
    private Long pkTaskSerialNumber;
    private Long pkTagSerialNumber;
    private String tagName;
}
