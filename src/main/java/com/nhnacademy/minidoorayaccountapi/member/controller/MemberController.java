package com.nhnacademy.minidoorayaccountapi.member.controller;

import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
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
    public ResponseEntity<List<GetMemberDto>> getAllMembers() {
        return ResponseEntity.ok().body(memberService.getMembers());
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<GetMemberDto> getMember(@PathVariable String memberId) {
        return ResponseEntity.ok().body(memberService.getMember(memberId));
    }

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberDto postMemberDto) {
        memberService.createMember(postMemberDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(@PathVariable String memberId, @RequestBody MemberDto putMemberDto) {
        memberService.updateMember(memberId, putMemberDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }

}
