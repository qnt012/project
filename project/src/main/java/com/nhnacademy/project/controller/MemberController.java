package com.nhnacademy.project.controller;

import com.nhnacademy.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/auth/login")
    public String getLogin() {
        return "member/login";
    }


    @PostMapping("/auth/login")
    public String postLogin(@RequestParam("id") String id, @RequestParam("pwd") String password) {
        boolean loginSuccess = memberService.login(id, password);
        if (loginSuccess) return "redirect:/";
        return "redirect:/auth/login";
    }

    @GetMapping("/signUp")
    public String getSignUp() {
        return "member/signUp";
    }

    @PostMapping("/signUp")
    public String postSignUp(@RequestParam("id") String id,
                             @RequestParam("pwd") String password,
                             @RequestParam("email") String email){

        memberService.createMember(id, password, email);
        return "redirect:/";
    }
}
