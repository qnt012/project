package com.nhnacademy.project.domain.request.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String access_token;
    private String scope;
    private String token_type;
}
