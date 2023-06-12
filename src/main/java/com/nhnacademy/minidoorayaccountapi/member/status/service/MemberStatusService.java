package com.nhnacademy.minidoorayaccountapi.member.status.service;

import com.nhnacademy.minidoorayaccountapi.member.status.dto.MemberStatusIdDto;

public interface MemberStatusService {
    void updateMemberStatus(String memberId, MemberStatusIdDto memberStatusIdDto);
}
