package com.nhnacademy.minidoorayaccountapi.member.status.service;

import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberException;
import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberStatusException;
import com.nhnacademy.minidoorayaccountapi.member.dto.UpdateMemberStatusAuthorityDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;
import com.nhnacademy.minidoorayaccountapi.member.status.repository.MemberStatusRepository;
import com.nhnacademy.minidoorayaccountapi.member.status.dto.MemberStatusIdDto;
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

        MemberStatus newMemberStatus = memberStatusRepository.findById(memberStatusIdDto.getMemberStatusId())
                .orElseThrow(() -> new NotFoundMemberStatusException(memberStatusIdDto.getMemberStatusId()));

        UpdateMemberStatusAuthorityDto updateMemberStatusAuthorityDto = new UpdateMemberStatusAuthorityDto(newMemberStatus);

        member.updateMemberStatusOrAuthority(updateMemberStatusAuthorityDto);
        memberRepository.saveAndFlush(member);
    }
}
