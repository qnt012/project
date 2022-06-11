package com.nhnacademy.project.domain.request.member;

import lombok.Data;

@Data
public class MemberLoginRequest {
    private final String id;
    private final String password;
}
