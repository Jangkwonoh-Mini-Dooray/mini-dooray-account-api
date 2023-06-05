package com.nhnacademy.minidoorayaccountapi.member.controller;

import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.getMembers());
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDto> getMember(@PathVariable String memberId) {
        return ResponseEntity.ok(memberService.getMember(memberId));
    }

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberDto memberDto) {
        memberService.createMember(memberDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(@PathVariable String memberId, @RequestBody MemberDto memberDto) {
        memberService.updateMember(memberId, memberDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }

}
