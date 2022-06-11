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

    @GetMapping("/{serialNumber}")
    public Member getMember(@PathVariable Long serialNumber) {
        return memberService.getMember(serialNumber).orElse(null);
    }

    @GetMapping("/active/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void getActive(@PathVariable Long serialNumber) {
        memberService.updateMemberStateActive(serialNumber);
    }

    @GetMapping("/dormant/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void getDormant(@PathVariable Long serialNumber) {
        memberService.updateMemberStateDormant(serialNumber);
    }

    @GetMapping("/withdraw/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void getWithdraw(@PathVariable Long serialNumber) {
        memberService.updateMemberStateWithdraw(serialNumber);
    }
}
