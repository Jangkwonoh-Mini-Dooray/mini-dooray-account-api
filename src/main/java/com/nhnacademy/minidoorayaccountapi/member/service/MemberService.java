package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.member.authority.entity.MemberAuthority;
import com.nhnacademy.minidoorayaccountapi.member.dto.GetMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.PostMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.dto.PutMemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import com.nhnacademy.minidoorayaccountapi.member.status.entity.MemberStatus;

import java.util.List;

public interface MemberService {
    List<GetMemberDto> getMembers();

    GetMemberDto getMember(String memberId);

    GetMemberDto getMemberByEmail(String email);

    Member createMember(PostMemberDto postMemberDto, MemberStatus defaultStatus, MemberAuthority defaultAuthority);

    Member updateMember(String memberId, PutMemberDto memberDto);

    void deleteMember(String memberId);
}
