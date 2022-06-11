package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

@Data
public class ProjectMemberCreateRequest {
    private final Long projectSerialNumber;
    private final String memberId;
}
