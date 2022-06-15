package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class MilestoneModifyRequest {
    @NotBlank
    @Size(max=20)
    private final String name;
}
