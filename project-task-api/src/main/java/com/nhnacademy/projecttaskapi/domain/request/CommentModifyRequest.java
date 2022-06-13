package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CommentModifyRequest {
    private final Long serialNumber;

    @NotBlank
    @Size(max=300)
    private final String content;
}
