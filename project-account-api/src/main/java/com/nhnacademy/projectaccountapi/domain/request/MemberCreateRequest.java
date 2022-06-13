package com.nhnacademy.projectaccountapi.domain.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class MemberCreateRequest {
    @NotBlank
    @Size(max=20)
    private String id;

    private String password;

    @Email
    @NotBlank
    @Size(max=30)
    private String email;
}
