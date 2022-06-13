package com.nhnacademy.projecttaskapi.domain.request;

import java.util.List;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class TaskModifyRequest {
    private final Long taskSerialNumber;

    @NotBlank
    @Size(max=30)
    private final String title;

    @NotBlank
    @Size(max=300)
    private final String content;

    private final Long milestoneSerialNumber;
    private final List<Long> tagSerialNumbers;
}
