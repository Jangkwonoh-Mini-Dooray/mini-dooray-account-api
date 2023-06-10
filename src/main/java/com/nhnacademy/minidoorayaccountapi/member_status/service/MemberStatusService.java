package com.nhnacademy.minidoorayaccountapi.member_status.service;

import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;
import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusIdDto;

public interface MemberStatusService {
    void updateMemberStatus(String memberId, MemberStatusIdDto memberStatusIdDto);
}
