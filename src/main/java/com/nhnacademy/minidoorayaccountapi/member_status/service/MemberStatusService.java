package com.nhnacademy.minidoorayaccountapi.member_status.service;

import com.nhnacademy.minidoorayaccountapi.member_status.dto.MemberStatusDto;

public interface MemberStatusService {
    MemberStatusDto getMemberStatus(int memberStatusId);

    void updateMemberStatus(int memberStatusId, MemberStatusDto memberStatusDto);
}
