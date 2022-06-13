package com.nhnacademy.project.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long serialNumber;
    private String projectMemberPkMemberId;
    private String content;
}
