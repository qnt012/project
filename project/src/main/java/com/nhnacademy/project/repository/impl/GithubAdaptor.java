package com.nhnacademy.project.repository.impl;

import com.nhnacademy.project.domain.request.member.Token;
import com.nhnacademy.project.domain.request.member.UserResponse;
import com.nhnacademy.project.repository.OauthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
@RequiredArgsConstructor
public class GithubAdaptor implements OauthRepository {
    private final RestTemplate restTemplate;

    @Override
    public Token getAccessToken(String clientId, String clientSecret, String code) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("github.com")
                .path("/login/oauth/access_token")
                .queryParam("client_id","af88cc84ce6da8787c0b" )
                .queryParam("client_secret", "cb2d5097641dbc2c0384b068cb4f9c864a59ec15")
                .queryParam("code", code)
                .build();

        ResponseEntity<Token> response = restTemplate.exchange(
                uriComponents.toUri(),
                HttpMethod.POST,
                null,
                Token.class
        );
        return response.getBody();
    }

    @Override
    public UserResponse getUser(String accessToken) {
        UriComponents userUriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.github.com")
                .path("/user")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token "+accessToken);
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<UserResponse> userResponse = restTemplate.exchange(
                userUriComponents.toUri(),
                HttpMethod.GET,
                httpEntity,
                UserResponse.class
        );
        return userResponse.getBody();
    }
}
