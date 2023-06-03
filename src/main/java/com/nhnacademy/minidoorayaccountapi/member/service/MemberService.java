package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;

import java.util.List;

public interface MemberService {
    List<MemberDto> getMembers();

    List<MemberDto> getMember(Long memberId);
//
//    Member createMember(Member member);
//
//    String deleteMember(String memberId);
}
