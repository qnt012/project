package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

@Data
public class MilestoneCreateRequest {
    private final Long projectSerialNumber;
    private final String name;
}
