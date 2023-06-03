package com.nhnacademy.minidoorayaccountapi.member.controller;

import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.service.DefaultMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final DefaultMemberService memberService;

    @GetMapping
    public List<MemberDto> getMembers() {
        return memberService.getMembers();
    }

    @GetMapping("/{memberId}")
    public MemberDto getMember(@PathVariable String memberId) {
        return memberService.getAccount(memberId);
    }
}
