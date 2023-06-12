package com.nhnacademy.minidoorayaccountapi.member.controller;

import com.nhnacademy.minidoorayaccountapi.exception.ValidationFailedException;
import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.PostMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.RespMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.PutMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.service.MemberService;
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
        return new ResponseEntity<>(memberService.getMembers(), HttpStatus.OK);
    }


    @GetMapping("/{memberId}")
    public ResponseEntity<GetMemberDto> getMember(@PathVariable String memberId) {
        return new ResponseEntity<>(memberService.getMember(memberId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RespMemberDto> createMember(@Valid @RequestBody PostMemberDto postMemberDto,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Member member = memberService.createMember(postMemberDto);
        RespMemberDto responseDto = new RespMemberDto(member.getMemberId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @PutMapping("/{memberId}")
    public ResponseEntity<RespMemberDto> updateMember(@PathVariable String memberId, @Valid @RequestBody PutMemberDto putMemberDto,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        Member member = memberService.updateMember(memberId, putMemberDto);
        RespMemberDto responseDto = new RespMemberDto(member.getMemberId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
