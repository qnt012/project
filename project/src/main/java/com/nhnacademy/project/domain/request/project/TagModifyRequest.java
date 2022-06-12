package com.nhnacademy.project.domain.request.project;

import lombok.Data;

@Data
public class TagModifyRequest {
    private final Long serialNumber;
    private final String name;
}
