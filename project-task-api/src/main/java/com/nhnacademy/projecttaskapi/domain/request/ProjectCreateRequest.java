package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

@Data
public class ProjectCreateRequest {
    private final Long adminSerialNumber;
    private final String name;
}
