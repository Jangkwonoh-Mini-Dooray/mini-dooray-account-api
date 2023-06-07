package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;

import java.util.List;

public interface MemberService {
    List<GetMemberDto> getMembers();

    GetMemberDto getMember(String memberId);

    void createMember(MemberDto memberDto);

    void updateMember(String memberId, MemberDto memberDto);

    void deleteMember(String memberId);
}
