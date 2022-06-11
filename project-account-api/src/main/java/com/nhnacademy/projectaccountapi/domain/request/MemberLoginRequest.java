package com.nhnacademy.projectaccountapi.domain.request;

import lombok.Data;

@Data
public class MemberLoginRequest {
    private final String id;
    private final String password;
}
