package com.nhnacademy.project.repository;

import com.nhnacademy.project.domain.request.member.Token;
import com.nhnacademy.project.domain.request.member.UserResponse;

public interface OauthRepository {
    Token getAccessToken(String clientId, String clientSecret, String code);
    UserResponse getUser(String accessToken);
}
