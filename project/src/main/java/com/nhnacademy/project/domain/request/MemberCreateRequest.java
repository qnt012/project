package com.nhnacademy.project.domain.request;

import lombok.Data;

@Data
public class MemberCreateRequest {
    private final String id;
    private final String password;
    private final String email;
}
