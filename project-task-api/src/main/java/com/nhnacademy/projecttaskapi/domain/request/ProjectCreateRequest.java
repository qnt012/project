package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProjectCreateRequest {
    private final String adminId;

    @NotBlank
    @Size(max=30)
    private final String name;
}
