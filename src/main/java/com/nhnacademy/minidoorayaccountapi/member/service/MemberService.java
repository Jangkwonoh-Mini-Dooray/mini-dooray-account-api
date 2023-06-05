package com.nhnacademy.minidoorayaccountapi.member.service;

import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;

import java.util.List;

public interface MemberService {
    List<MemberDto> getMembers();

    MemberDto getMember(String memberId);

    void createMember(MemberDto memberDto);

    void updateMember(String memberId, MemberDto memberDto);

    void deleteMember(String memberId);
}
