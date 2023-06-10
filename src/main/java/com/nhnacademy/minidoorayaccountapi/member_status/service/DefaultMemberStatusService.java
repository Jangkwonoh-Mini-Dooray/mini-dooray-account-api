package com.nhnacademy.minidoorayaccountapi.member_status.service;

import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberException;
import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberStatusException;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusIdDto;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;
import com.nhnacademy.minidoorayaccountapi.member_status.repository.MemberStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultMemberStatusService implements MemberStatusService{
    private final MemberStatusRepository memberStatusRepository;
    private final MemberRepository memberRepository;

    @Override
    public void updateMemberStatus(String memberId, MemberStatusIdDto memberStatusIdDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException(memberId));

        MemberStatus memberStatus = memberStatusRepository.findById(memberStatusIdDto.getMemberStatusId())
                .orElseThrow(() -> new NotFoundMemberStatusException(memberStatusIdDto.getMemberStatusId()));

        member.setMemberStatus(memberStatus);
        memberRepository.saveAndFlush(member);
    }
}
