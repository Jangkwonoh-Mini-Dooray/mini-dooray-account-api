package com.nhnacademy.minidoorayaccountapi.member_status.controller;

import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberException;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
import com.nhnacademy.minidoorayaccountapi.member_status.service.MemberStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(memberStatusService.getMemberStatus(memberStatus.getMemberStatusId()));
    }

    @PutMapping
    public ResponseEntity<Void> updateMemberStatus(@PathVariable("member-id") String memberId,
                                                      @RequestBody MemberStatusDto memberStatusDto) {
        MemberStatus memberStatus = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException(memberId))
                .getMemberStatus();
        memberStatusService.updateMemberStatus(memberStatus.getMemberStatusId(), memberStatusDto);
        return ResponseEntity.ok().build();
    }
}
