package com.nhnacademy.minidoorayaccountapi.member_authority.service;

import com.nhnacademy.minidoorayaccountapi.exception.NotFoundAuthorityException;
import com.nhnacademy.minidoorayaccountapi.exception.NotFoundMemberException;
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
    public MemberAuthorityDto getMemberAuthority(int memberAuthorityId) {
        return memberAuthorityRepository.getMemberAuthority(memberAuthorityId);
    }

    @Override
    public void updateMemberAuthority(int memberAuthorityId, MemberAuthorityDto memberAuthorityDto) {
        MemberAuthority memberAuthority = memberAuthorityRepository.findById(memberAuthorityId)
                .orElseThrow(() -> new NotFoundAuthorityException(memberAuthorityId));
        memberAuthority.setStatus(memberAuthorityDto.getStatus());

        memberAuthorityRepository.save(memberAuthority);
    }
}
