package com.nhnacademy.minidoorayaccountapi.member_authority.service;

import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberAuthorityException;
import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberException;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.repository.MemberRepository;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityIdDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member_authority.repository.MemberAuthorityRepository;
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

        member.setMemberAuthority(newMemberAuthority);
        memberRepository.saveAndFlush(member);
    }

}
