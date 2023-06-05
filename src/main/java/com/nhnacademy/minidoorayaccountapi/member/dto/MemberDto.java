package com.nhnacademy.minidoorayaccountapi.member.dto;

import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member_status.entity.MemberStatus;

public interface MemberDto {
    String getMemberId();
    String getPassword();
    String getEmail();
    String getName();
}
