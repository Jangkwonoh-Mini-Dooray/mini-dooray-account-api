package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;

import java.util.List;

public interface MemberService {
    List<MemberDto> getMembers();

    MemberDto getMember(Long memberId);

    MemberDto createMember(MemberDto memberDto);

    String updateMemberDto(Long memberId);

    String deleteMemberDto(Long memberId);
}
