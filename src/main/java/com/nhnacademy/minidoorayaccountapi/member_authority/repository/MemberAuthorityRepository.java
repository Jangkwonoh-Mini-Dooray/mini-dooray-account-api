package com.nhnacademy.minidoorayaccountapi.member_authority.repository;

import com.nhnacademy.minidoorayaccountapi.member.dto.MemberDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.dto.MemberAuthorityDto;
import com.nhnacademy.minidoorayaccountapi.member_authority.entity.MemberAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberAuthorityRepository extends JpaRepository<MemberAuthority, Integer> {
}
