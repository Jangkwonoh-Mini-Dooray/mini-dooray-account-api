package com.nhnacademy.minidoorayaccountapi.member.controller;

import com.nhnacademy.minidoorayaccountapi.exception.MemberBindingResultException;
import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.MemberIdDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<GetMemberDto>> getMembers() {
        return new ResponseEntity<>(memberService.getMembers(), HttpStatus.OK);
    }


    @GetMapping("/{memberId}")
    public ResponseEntity<GetMemberDto> getMember(@PathVariable String memberId) {
        return ResponseEntity.ok().body(memberService.getMember(memberId));
    }

    @PostMapping
    public ResponseEntity<MemberIdDto> createMember(@RequestBody MemberDto postMemberDto,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MemberBindingResultException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Member member = memberService.createMember(postMemberDto);
        MemberIdDto responseDto = new MemberIdDto(member.getMemberId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
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
