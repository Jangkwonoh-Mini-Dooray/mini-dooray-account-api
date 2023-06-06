package com.nhnacademy.minidoorayaccountapi.member_authority.service;

import com.nhnacademy.minidoorayaccountapi.exception.MemberNotFoundException;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member_authority.repository.MemberAuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultMemberAuthorityService implements MemberAuthorityService{
    private final MemberAuthorityRepository memberAuthorityRepository;

    @Override
    public MemberAuthorityDto getMemberAuthority(int authorityId) {
        return memberAuthorityRepository.getAuthorityByAuthorityId(authorityId);
    }

    @Override
    public void updateMemberAuthority(int authorityId, MemberAuthorityDto memberAuthorityDto) {
        MemberAuthority memberAuthority = memberAuthorityRepository.findById(authorityId)
                .orElseThrow(() -> new MemberNotFoundException());
        memberAuthority.setStatus(memberAuthorityDto.getStatus());

        memberAuthorityRepository.save(memberAuthority);
    }
}
