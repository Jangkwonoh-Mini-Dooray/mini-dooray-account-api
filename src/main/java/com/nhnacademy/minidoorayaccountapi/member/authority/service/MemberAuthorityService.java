package com.nhnacademy.minidoorayaccountapi.member.authority.service;

import com.nhnacademy.minidoorayaccountapi.member.authority.dto.MemberAuthorityIdDto;

public interface MemberAuthorityService {
    void updateMemberAuthority(String memberId, MemberAuthorityIdDto memberAuthorityIdDto);
}
