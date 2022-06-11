package com.nhnacademy.project.controller;

import com.nhnacademy.project.service.OauthLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class OauthController {
    private final OauthLoginService oauthLoginService;

    public OauthController(OauthLoginService oauthLoginService) {
        this.oauthLoginService = oauthLoginService;
    }

    @GetMapping("/oauth/github")
    public String getOauthGithub() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("github.com")
                .path("/login/oauth/authorize")
                .queryParam("client_id", "af88cc84ce6da8787c0b")
                .build();
        return "redirect:"+uriComponents;
    }


    @GetMapping("/login/oauth2/code/github")
    public String getOauthGithubCode(@RequestParam String code) {
        if (!oauthLoginService.login(code)) return "redirect:/logout";
        return "redirect:/";
    }
}
