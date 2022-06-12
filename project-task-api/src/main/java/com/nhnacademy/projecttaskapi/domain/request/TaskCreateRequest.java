package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class TaskCreateRequest {
    private final Long projectSerialNumber;
    private final String memberId;
    private final String title;
    private final String content;
    private final Long milestoneSerialNumber;
    private final List<Long> tagSerialNumbers;
}
