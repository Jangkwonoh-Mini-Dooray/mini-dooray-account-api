package com.nhnacademy.minidoorayaccountapi.member.authority.service;

import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberAuthorityException;
import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberException;
import com.nhnacademy.minidoorayaccountapi.member.authority.dto.MemberAuthorityIdDto;
import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member.dto.UpdateMemberStatusAuthorityDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member.authority.repository.MemberAuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultMemberAuthorityService implements MemberAuthorityService{
    private final MemberAuthorityRepository memberAuthorityRepository;
    private final MemberRepository memberRepository;

    @Override
    public void updateMemberAuthority(String memberId, MemberAuthorityIdDto memberAuthorityIdDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException(memberId));

        MemberAuthority newMemberAuthority = memberAuthorityRepository.findById(memberAuthorityIdDto.getMemberAuthorityId())
                .orElseThrow(() -> new NotFoundMemberAuthorityException(memberAuthorityIdDto.getMemberAuthorityId()));

        UpdateMemberStatusAuthorityDto updateMemberStatusAuthorityDto = new UpdateMemberStatusAuthorityDto(newMemberAuthority);
        member.updateMemberStatusOrAuthority(updateMemberStatusAuthorityDto);
        memberRepository.saveAndFlush(member);
    }

}
