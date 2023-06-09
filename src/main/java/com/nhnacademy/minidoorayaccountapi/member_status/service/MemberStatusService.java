package com.nhnacademy.minidoorayaccountapi.member_status.service;

import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;

public interface MemberStatusService {
    void updateMemberStatus(int memberStatusId, MemberStatusDto memberStatusDto);
}
