package com.nhnacademy.projectaccountapi.controller;

import com.nhnacademy.projectaccountapi.domain.request.MemberCreateRequest;
import com.nhnacademy.projectaccountapi.entity.Member;
import com.nhnacademy.projectaccountapi.repository.ValidationFailedException;
import com.nhnacademy.projectaccountapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public void postInsert(@Valid @RequestBody MemberCreateRequest request,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        memberService.createMember(request);
    }

    @GetMapping("/{id}")
    public Member getMember(@PathVariable String id) {
        return memberService.getMember(id).orElse(null);
    }

    @GetMapping("/email/{email}")
    public Member getMemberByEmail(@PathVariable String email) {
        return memberService.getMemberByEmail(email).orElse(null);
    }

    @GetMapping("/active/{id}")
    public void getActive(@PathVariable String id) {
        memberService.updateMemberStateActive(id);
    }

    @GetMapping("/dormant/{id}")
    public void getDormant(@PathVariable String id) {
        memberService.updateMemberStateDormant(id);
    }

    @GetMapping("/withdraw/{id}")
    public void getWithdraw(@PathVariable String id) {
        memberService.updateMemberStateWithdraw(id);
    }
}
