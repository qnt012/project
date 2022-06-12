package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

@Data
public class MilestoneModifyRequest {
    private final Long serialNumber;
    private final String name;
}
