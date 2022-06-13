package com.nhnacademy.projecttaskapi.domain.request;

import java.util.List;
import lombok.Data;


@Data
public class TaskModifyRequest {
    private final Long taskSerialNumber;
    private final Long projectSerialNumber;
    private final String memberId;
    private final String title;
    private final String content;
    private final Long milestoneSerialNumber;
    private final List<Long> tagSerialNumbers;
}
