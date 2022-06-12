package com.nhnacademy.projecttaskapi.domain.request;

import lombok.Data;

@Data
public class TagModifyRequest {
    private final Long serialNumber;
    private final String name;
}
