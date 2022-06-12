package com.nhnacademy.project.domain.request.project;

import lombok.Data;

@Data
public class MilestoneModifyRequest {
    private final Long serialNumber;
    private final String name;
}
