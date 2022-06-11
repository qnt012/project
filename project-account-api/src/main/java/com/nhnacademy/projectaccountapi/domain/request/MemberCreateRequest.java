package com.nhnacademy.projectaccountapi.domain.request;

import lombok.Data;

@Data
public class MemberCreateRequest {
    private String id;
    private String password;
    private String email;
}
