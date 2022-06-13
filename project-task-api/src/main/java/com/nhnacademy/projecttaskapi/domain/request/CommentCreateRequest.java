package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CommentCreateRequest {
    private final Long taskSerialNumber;
    private final String memberId;
    private final Long projectSerialNumber;

    @NotBlank
    @Size(max=300)
    private final String content;
}
