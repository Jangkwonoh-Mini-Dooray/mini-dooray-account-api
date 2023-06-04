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
    public MemberDto getMember(@PathVariable Long memberId) {
        return memberService.getMember(memberId);
    }

    @PostMapping
    public MemberDto createMember(@RequestBody MemberDto memberDto) {
        return memberService.createMember(memberDto);
    }

    @PutMapping("/{memberId}")
    public String updateMember(@PathVariable Long memberId) {
        return memberService.updateMemberDto(memberId);
    }

    @DeleteMapping("/{memberId}")
    public String deleteMember(@PathVariable Long memberId) {
        return memberService.deleteMemberDto(memberId);
    }
}
