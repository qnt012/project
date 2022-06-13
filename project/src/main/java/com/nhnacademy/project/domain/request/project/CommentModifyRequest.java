package com.nhnacademy.project.domain.request.project;

import lombok.Data;

@Data
public class CommentModifyRequest {
    private final Long serialNumber;
    private final String content;
}
