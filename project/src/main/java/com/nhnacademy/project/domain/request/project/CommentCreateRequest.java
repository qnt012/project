package com.nhnacademy.project.domain.request.project;

import lombok.Data;

@Data
public class CommentCreateRequest {
    private final Long taskSerialNumber;
    private final String memberId;
    private final Long projectSerialNumber;
    private final String content;
}
