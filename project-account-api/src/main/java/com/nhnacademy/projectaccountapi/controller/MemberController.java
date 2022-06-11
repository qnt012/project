package com.nhnacademy.projectaccountapi.controller;

import com.nhnacademy.projectaccountapi.domain.request.MemberCreateRequest;
import com.nhnacademy.projectaccountapi.domain.request.MemberLoginRequest;
import com.nhnacademy.projectaccountapi.entity.Member;
import com.nhnacademy.projectaccountapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public void postInsert(@RequestBody MemberCreateRequest request) {
        memberService.createMember(request);
    }

    @PostMapping("/login")
    public Member postLogin(@RequestBody MemberLoginRequest request) {
        return memberService.login(request.getId(), request.getPassword()).orElse(null);
    }

    @GetMapping("/{id}")
    public Member getMember(@PathVariable String id) {
        return memberService.getMember(id).orElse(null);
    }

    @GetMapping("/active/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void getActive(@PathVariable String id) {
        memberService.updateMemberStateActive(id);
    }

    @GetMapping("/dormant/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void getDormant(@PathVariable String id) {
        memberService.updateMemberStateDormant(id);
    }

    @GetMapping("/withdraw/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void getWithdraw(@PathVariable String id) {
        memberService.updateMemberStateWithdraw(id);
    }
}
