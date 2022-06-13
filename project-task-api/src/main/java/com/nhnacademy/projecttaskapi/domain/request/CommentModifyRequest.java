package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

@Data
public class CommentModifyRequest {
    private final Long serialNumber;
    private final String content;
}
