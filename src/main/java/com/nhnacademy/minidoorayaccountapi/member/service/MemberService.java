package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;

import java.util.List;

public interface MemberService {
    List<GetMemberDto> getMembers();

    GetMemberDto getMember(String memberId);

    Member createMember(MemberDto memberDto);

    Member updateMember(String memberId, MemberDto memberDto);

    void deleteMember(String memberId);
}
