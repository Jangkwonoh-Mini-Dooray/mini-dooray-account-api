package com.nhnacademy.minidoorayaccountapi.member.repository;

import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<MemberDto> findAllMemberDto();

    MemberDto findMemberDto(Long memberId);

    MemberDto createMemberDto(MemberDto memberDto);

    String deleteMemberDto(Long memberId);
}
