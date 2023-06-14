package com.nhnacademy.minidoorayaccountapi.member.authority.controller;

import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberException;
import com.nhnacademy.minidoorayaccountapi.exception.ValidationFailedException;
import com.nhnacademy.minidoorayaccountapi.member.authority.dto.MemberAuthorityIdDto;
import com.nhnacademy.minidoorayaccountapi.member.authority.dto.MemberAuthorityStatusDto;
import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member.authority.service.MemberAuthorityService;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members/{member-id}/authority")
@RequiredArgsConstructor
public class MemberAuthorityController {
    private final MemberRepository memberRepository;
    private final MemberAuthorityService memberAuthorityService;
    @GetMapping
    public ResponseEntity<MemberAuthorityStatusDto> getMemberAuthority(@PathVariable("member-id") String memberId) {
        MemberAuthority memberAuthority = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException(memberId))
                .getMemberAuthority();
        MemberAuthorityStatusDto responseDto = new MemberAuthorityStatusDto(memberAuthority.getStatus());
        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping
    public ResponseEntity<Response> updateMemberAuthority(@PathVariable("member-id") String memberId,
                                                             @Valid @RequestBody MemberAuthorityIdDto memberAuthorityIdDto,
                                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        memberAuthorityService.updateMemberAuthority(memberId, memberAuthorityIdDto);
        return ResponseEntity.ok().body(new Response("OK"));
    }
}
