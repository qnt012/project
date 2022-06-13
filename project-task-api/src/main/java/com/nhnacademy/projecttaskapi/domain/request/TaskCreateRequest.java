package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class TaskCreateRequest {
    private final Long projectSerialNumber;
    private final String memberId;

    @NotBlank
    @Size(max=30)
    private final String title;

    @NotBlank
    @Size(max=300)
    private final String content;

    private final Long milestoneSerialNumber;
    private final List<Long> tagSerialNumbers;
}
