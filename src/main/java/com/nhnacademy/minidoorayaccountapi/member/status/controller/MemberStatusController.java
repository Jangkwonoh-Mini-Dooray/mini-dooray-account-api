package com.nhnacademy.minidoorayaccountapi.member.status.controller;

import com.nhnacademy.minidoorayaccountapi.exception.ValidationFailedException;
import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberException;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member.status.dto.MemberStatusDto;
import com.nhnacademy.minidoorayaccountapi.member.status.dto.MemberStatusIdDto;
import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;
import com.nhnacademy.minidoorayaccountapi.member.status.service.MemberStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members/{member-id}/status")
@RequiredArgsConstructor
public class MemberStatusController {
    private final MemberRepository memberRepository;
    private final MemberStatusService memberStatusService;
    @GetMapping
    public ResponseEntity<MemberStatusDto> getMemberStatus(@PathVariable("member-id") String memberId) {
        MemberStatus memberStatus = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException(memberId))
                .getMemberStatus();
        MemberStatusDto responseDto = new MemberStatusDto(memberStatus.getStatus());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateMemberStatus(@PathVariable("member-id") String memberId,
                                                   @Valid @RequestBody MemberStatusIdDto memberStatusIdDto,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        memberStatusService.updateMemberStatus(memberId, memberStatusIdDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
