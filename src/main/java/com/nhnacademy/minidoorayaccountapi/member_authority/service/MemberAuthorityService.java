package com.nhnacademy.minidoorayaccountapi.member_authority.service;

import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityDto;

public interface MemberAuthorityService {
    MemberAuthorityDto getMemberAuthority(int authorityId);

    void updateMemberAuthority(int authorityId, MemberAuthorityDto memberAuthorityDto);
}
