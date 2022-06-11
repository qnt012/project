package com.nhnacademy.project.domain.request.project;

import lombok.Data;

@Data
public class ProjectCreateRequest {
    private final String adminId;
    private final String name;
}
