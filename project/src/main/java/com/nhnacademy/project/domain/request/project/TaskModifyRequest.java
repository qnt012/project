package com.nhnacademy.project.domain.request.project;

import java.util.List;
import lombok.Data;


@Data
public class TaskModifyRequest {
    private final Long taskSerialNumber;
    private final String title;
    private final String content;
    private final Long milestoneSerialNumber;
    private final List<Long> tagSerialNumbers;
}
