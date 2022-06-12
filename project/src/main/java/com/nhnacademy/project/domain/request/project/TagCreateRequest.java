package com.nhnacademy.project.domain.request.project;

import lombok.Data;

@Data
public class TagCreateRequest {
    private final Long projectSerialNumber;
    private final String name;
}
