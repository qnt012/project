package com.nhnacademy.project.domain.request.project;

import lombok.Data;

@Data
public class ProjectMemberCreateRequest {
    private final Long projectSerialNumber;
    private final String memberId;
}
