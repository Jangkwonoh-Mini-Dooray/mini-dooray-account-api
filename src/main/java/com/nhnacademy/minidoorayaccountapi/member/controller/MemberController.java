package com.nhnacademy.minidoorayaccountapi.member.controller;

import com.nhnacademy.minidoorayaccountapi.exception.ValidationFailedException;
import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.PostMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.RespMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.PutMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.service.MemberService;
import com.nhnacademy.minidoorayaccountapi.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<GetMemberDto>> getMembers() {
        return ResponseEntity.ok().body(memberService.getMembers());
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<GetMemberDto> getMember(@PathVariable String memberId) {
        return ResponseEntity.ok().body(memberService.getMember(memberId));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<GetMemberDto> getMemberByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(memberService.getMemberByEmail(email));
    }

    @PostMapping
    public ResponseEntity<RespMemberDto> createMember(@Valid @RequestBody PostMemberDto postMemberDto,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Member member = memberService.createMember(postMemberDto);
        RespMemberDto responseDto = new RespMemberDto(member.getMemberId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    @PutMapping("/{memberId}")
    public ResponseEntity<RespMemberDto> updateMember(@PathVariable String memberId, @Valid @RequestBody PutMemberDto putMemberDto,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        Member member = memberService.updateMember(memberId, putMemberDto);
        RespMemberDto responseDto = new RespMemberDto(member.getMemberId());
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Response> deleteMember(@PathVariable String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("OK"));
    }

}
