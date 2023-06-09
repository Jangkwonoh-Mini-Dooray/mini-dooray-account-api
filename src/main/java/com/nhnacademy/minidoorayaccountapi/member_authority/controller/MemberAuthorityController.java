package com.nhnacademy.minidoorayaccountapi.member_authority.controller;

import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberException;
import com.nhnacademy.minidoorayaccountapi.exception.ValidationFailedException;
import com.nhnacademy.minidoorayaccountapi.member.dto.MemberIdDto;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member_authority.service.MemberAuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<MemberAuthorityDto> getMemberAuthority(@PathVariable("member-id") String memberId) {
        MemberAuthority memberAuthority = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException(memberId))
                .getMemberAuthority();
        MemberAuthorityDto responseDto = new MemberAuthorityDto(memberAuthority.getStatus());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MemberIdDto> updateMemberAuthority(@PathVariable("member-id") String memberId,
                                                             @Valid @RequestBody MemberAuthorityDto memberAuthorityDto,
                                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        MemberAuthority memberAuthority = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException(memberId))
                .getMemberAuthority();
        memberAuthorityService.updateMemberAuthority(memberAuthority.getMemberAuthorityId(), memberAuthorityDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
