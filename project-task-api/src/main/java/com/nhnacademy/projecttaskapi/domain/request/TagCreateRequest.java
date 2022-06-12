package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

@Data
public class TagCreateRequest {
    private final Long projectSerialNumber;
    private final String name;
}
