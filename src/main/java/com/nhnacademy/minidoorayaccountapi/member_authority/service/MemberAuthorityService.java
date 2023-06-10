package com.nhnacademy.minidoorayaccountapi.member_authority.service;

import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityIdDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityStatusDto;

public interface MemberAuthorityService {
    void updateMemberAuthority(String memberId, MemberAuthorityIdDto memberAuthorityIdDto);
}
